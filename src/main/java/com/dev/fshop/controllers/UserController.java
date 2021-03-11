package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.services.AccountService;
import com.dev.fshop.utils.AuthenticatedRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Data
class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "User")
public class UserController {
    @Autowired
    private AccountService accountService;

    @Operation(description = "get users", responses = {
            @ApiResponse(
                    description = "get users successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Account.class))
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
    @GetMapping("/users")
    public ResponseEntity getUsers(
            @RequestParam Optional<String> q,
            @RequestParam Optional<String> email,
            @RequestParam Optional<String> role,
            @RequestParam Optional<Integer> status,
            Authentication authentication,
            @RequestParam Optional<Integer> pageIndex,
            @RequestParam Optional<Integer> pageSize
    ) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        if (AuthenticatedRole.isAdmin(authentication)) {
            if (q.isPresent()) {
                Page<Account> accountList = accountService.searchAccountsByParameter("%" + q.orElse(null) + "%", pageable);
                if (accountList != null && !accountList.isEmpty()) {
                    return new ResponseEntity(accountList, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                Page<Account> accountList = accountService.searchAccountsByParameters(status.orElse(1), email.orElse(null), role.orElse(null), pageable);
                if (accountList != null && !accountList.isEmpty()) {
                    return new ResponseEntity(accountList, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                }
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }


    @Operation(description = "Create new user", responses = {
            @ApiResponse(
                    description = "Create new user successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new user successfully!",
                                    value = "Create new user successfully!"
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
    @PostMapping("/users/register")
    public ResponseEntity register(@Valid @RequestBody Account account) {
        accountService.addUser(account, "ROL_1");
        return new ResponseEntity("Create new user successfully!", HttpStatus.OK);
    }

    @Operation(description = "Create new staff", responses = {
            @ApiResponse(
                    description = "Create new staff successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new staff successfully!",
                                    value = "Create new staff successfully!"
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
    @PostMapping("/users")
    public ResponseEntity addStaff(@Valid @RequestBody Account account) {
        accountService.addUser(account, "ROL_2");
        return new ResponseEntity("Create new staff successfully!", HttpStatus.OK);
    }

    @Operation(description = "change password", responses = {
            @ApiResponse(
                    description = "change password successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "change password successfully!",
                                    value = "change password successfully!"
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
            @ApiResponse(
                    description = "Change failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Change failed!",
                                    value = "Change failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PostMapping("/users/{username}/change_password")
    public ResponseEntity changePassword(@PathVariable String username, @RequestBody ChangePasswordRequest request, Authentication authentication) {
        if (AuthenticatedRole.isMySelf(username, authentication)) {
            Account checkAccount = accountService.getUserByUsername(username);
            if (checkAccount != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (encoder.matches(request.getOldPassword(), checkAccount.getPassword())) {
                    if (request.getNewPassword().matches(request.getConfirmPassword())) {
                        if (!request.getNewPassword().matches(request.getOldPassword())) {
                            String hashPass = BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt());
                            boolean checkChangePassword = accountService.changePassword(checkAccount, hashPass);
                            if (checkChangePassword) {
                                return new ResponseEntity("change password successfully!", HttpStatus.OK);
                            }
                            return new ResponseEntity("Change failed!", HttpStatus.BAD_REQUEST);
                        } else {
                            return new ResponseEntity("New Password must be not match with Old Password", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity("Change failed!Confirm password does not match.", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity("Change failed!", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity("Not found!", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "update profile", responses = {
            @ApiResponse(
                    description = "update profile successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "update profile successfully!",
                                    value = "update profile successfully!"
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
            @ApiResponse(
                    description = "update profile failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "update profile failed!",
                                    value = "update profile failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PutMapping("/users/{username}")
    public ResponseEntity updateProfile(@PathVariable String username, @RequestBody Account account, Authentication authentication) {
        if (AuthenticatedRole.isMySelf(username, authentication)) {
            Account currentAccount = accountService.getUserByUsername(username);
            if (currentAccount != null) {
                accountService.updateProfile(currentAccount, account);
                return new ResponseEntity("update profile successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "ban account", responses = {
            @ApiResponse(
                    description = "ban account successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "ban account successfully!",
                                    value = "ban account successfully!"
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
            @ApiResponse(
                    description = "ban account failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "ban account failed!",
                                    value = "ban account failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PutMapping("/users/{username}/ban_account")
    public ResponseEntity banAccount(@PathVariable String username, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Account account = accountService.getUserByUsername(username);
            if (account != null) {
                account.setStatus(0);
                accountService.changeStatusAccount(account);
                return new ResponseEntity(account, HttpStatus.OK);
            } else {
                return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "active account", responses = {
            @ApiResponse(
                    description = "active account successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "active account successfully!",
                                    value = "active account successfully!"
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
            @ApiResponse(
                    description = "active account failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "active account failed!",
                                    value = "active account failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PutMapping("/users/{username}/active_account")
    public ResponseEntity activeAccount(@PathVariable String username, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Account account = accountService.getUserByUsername(username);
            if (account != null) {
                account.setStatus(1);
                accountService.changeStatusAccount(account);
                return new ResponseEntity(account, HttpStatus.OK);
            } else {
                return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "get user by username", responses = {
            @ApiResponse(
                    description = "get user by username successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Account.class)
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
    @GetMapping("/users/{username}")
    public ResponseEntity getByUsername(@PathVariable String username, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Account account = accountService.getUserByUsername(username);
            if (account != null) {
                return new ResponseEntity(accountService.getUserByUsername(username), HttpStatus.OK);
            }
            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
        } else if (AuthenticatedRole.isMySelf(username, authentication)) {
            Account account = accountService.getUserByUsername(username);
            if (account != null) {
                return new ResponseEntity(accountService.getUserByUsername(username), HttpStatus.OK);
            }
            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

//    @GetMapping("/users/contacts")
//    public ResponseEntity getContacts(Authentication authentication, @RequestParam Optional<Integer> pageIndex,
//                                      @RequestParam Optional<Integer> pageSize) {
//        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
//        System.out.println(pageIndex.orElse(1) - 1);
//        if (AuthenticatedRole.isUser(authentication)) {
//            return new ResponseEntity(accountService.searchAccountsByParameters(null, "ADMIN",pageable).getContent(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity(accountService.searchAccountsByParameters(null, "USER",pageable).getContent(), HttpStatus.OK);
//        }
//    }
}
