package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "User")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(description = "Login (ALL ROLE), Accept Form Data", responses = {
            @ApiResponse(
                    description = "Login Successfully",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user login successfully!",
                                    value = "Login Successfully!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Unauthenticated!",
                    responseCode = "401",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Unauthenticated!",
                                    value = "Username or password is incorrect!"),
                            schema = @Schema(implementation = String.class))
            ),
    })
    @PostMapping(path = "/users/login")
    public ResponseEntity<Account> login(HttpServletRequest request){
        return null;
    }


    @Operation(description = "Register", responses = {
            @ApiResponse(
                    description = "Register Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when register successfully!",
                                    value = "Register Successfully"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Create failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when role is not existed!",
                                    value = "Role is not existed!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping(path = "/users")
    public ResponseEntity<Account> registerAccount(
            @RequestBody
            @Valid
            @Parameter(
                    description = "Comment model to create.",
                    required = true,
                    schema = @Schema(implementation = Account.class)
            ) Account account) {
        return ResponseEntity.ok().body(userService.registerAccountUser(account));
    }

    @Operation(description = "Find user", responses = {
            @ApiResponse(
                    description = "Get Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Account.class)))
            ),
            @ApiResponse(
                    description = "Access deny!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user is not authorized!",
                                    value = "Access deny!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when this id or name is not found!",
                                    value = "Product is not found!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping(path = "/users")
    public ResponseEntity<Account> findUser(
            @RequestParam Optional<String> userId,
            @RequestParam Optional<String> userName) {
        return null;
    }

    @Operation(description = "Change Pass", responses = {
            @ApiResponse(
                    description = "Change password Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when register successfully!",
                                    value = "Register Successfully"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Access deny!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Unauthorized!",
                                    value = "Unauthorized!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Change password failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Old password is incorrect!",
                                    value = "Old password is incorrect!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @PatchMapping(path = "/users")
    public ResponseEntity<Account> changePassword(HttpServletRequest request, Authentication authentication) {
        String userId = request.getParameter("userId");
        String newPass = request.getParameter("newPass");
        String oldPass = request.getParameter("oldPass");
        return ResponseEntity.ok().body(userService.changePassword(userId,newPass,oldPass));
    }

    @Operation(description = "Edit Profile", responses = {
            @ApiResponse(
                    description = "Edit Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when update profile successfully!",
                                    value = "Update Successfully"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Access deny!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user is not authorized!",
                                    value = "Access deny!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Update failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when role is not existed!",
                                    value = "Role is not existed!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<Account> updateProfileUser(@PathVariable String userId, @RequestBody Account account) {
        return ResponseEntity.ok().body(userService.updateProfileUser(userId, account));
    }

    @Operation(description = "Delete User", responses = {
            @ApiResponse(
                    description = "Delete Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when update profile successfully!",
                                    value = "Update Successfully"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Access deny!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user is not authorized!",
                                    value = "Access deny!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @DeleteMapping(path = "/users/{userId}")
    public boolean removeAccountUserExisted(@PathVariable String userId) {
        return userService.removeAccountUser(userId);
    }

}
