package com.dev.fshop.controllers;

import com.dev.fshop.entities.Category;
import com.dev.fshop.entities.Product;
import com.dev.fshop.entities.Supplier;
import com.dev.fshop.services.CategoryService;
import com.dev.fshop.utils.AuthenticatedRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(description = "get categories", responses = {
            @ApiResponse(
                    description = "get categories successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)
                    )
            ),
            @ApiResponse(
                    description = "Not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Not found!",
                                    value = "Not found!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @GetMapping("/categories")
    public ResponseEntity getCategories(
            @RequestParam Optional<String> proTypeName,
            @RequestParam Optional<Integer> pageIndex,
            @RequestParam Optional<Integer> pageSize
    ) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        Page<Category> productList = categoryService.searchCategoriesWithCategoryName(proTypeName.orElse(null), pageable);
        if (productList != null && !productList.isEmpty()) {
            return new ResponseEntity(productList, HttpStatus.OK);
        } else {
            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Create new Category", responses = {
            @ApiResponse(
                    description = "Create new category successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new category successfully!",
                                    value = "Create new category successfully!"
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
            @ApiResponse(
                    description = "Duplicated name of category!",
                    responseCode = "409",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Duplicated name of category!",
                                    value = "Duplicated name of category!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PostMapping("/categories")
    public ResponseEntity createSupplier(@RequestBody Category category, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            List<Category> checkCategoryExisted = categoryService.checkCategoriesByCategoryName(category.getProTypeName());
            System.out.println(checkCategoryExisted);
            if (checkCategoryExisted == null || checkCategoryExisted.size() == 0) {
                categoryService.createNewCategory(category);
                return new ResponseEntity("Create Category successful!", HttpStatus.OK);
            } else {
                return new ResponseEntity("Duplicated name of category!", HttpStatus.valueOf(409));
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

}
