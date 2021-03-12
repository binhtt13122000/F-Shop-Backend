package com.dev.fshop.controllers;

import com.dev.fshop.entities.Category;
import com.dev.fshop.entities.Product;
import com.dev.fshop.entities.Supplier;
import com.dev.fshop.services.SupplierService;
import com.dev.fshop.utils.AuthenticatedRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Operation(description = "Create new supplier", responses = {
            @ApiResponse(
                    description = "Create new supplier successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new supplier successfully!",
                                    value = "Create new supplier successfully!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Access denied!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Access denied!",
                                    value = "Access denied!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Create failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create failed!",
                                    value = "Create failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PostMapping("/suppliers")
    public ResponseEntity createSupplier(@RequestBody Supplier supplier, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            List<Supplier> checkSupplierExisted = supplierService.searchSupplierBySupplierName(supplier.getSupplierName());
            if (checkSupplierExisted != null && checkSupplierExisted.size() != 0) {
                String lastSupplierName = checkSupplierExisted.get(checkSupplierExisted.size() - 1).getSupplierName();
                String[] splitSupplierName = lastSupplierName.split("-");
                    Integer number = Integer.parseInt(splitSupplierName[1]);
                    String newSupplierName = splitSupplierName[0] + "-" + String.format("%03d", (++number));
                    supplier.setSupplierName(newSupplierName);
                supplierService.createNewSupplier(supplier);
                return new ResponseEntity("Create Supplier successful!", HttpStatus.OK);
            } else {
                String newSupplierName = supplier.getSupplierName() + "-" + String.format("%03d", (1));
                supplier.setSupplierName(newSupplierName);
                supplierService.createNewSupplier(supplier);
                return new ResponseEntity("Create Supplier successful!", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }
}
