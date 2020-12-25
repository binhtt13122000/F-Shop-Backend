package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            @RequestParam Optional<String> role
    ) {
        return null;
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
        return new ResponseEntity("Create new user successfully!", HttpStatus.OK);
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

    //change password
    //check newPass với confirm Pass phải equal,oldPass encode -> trùng với cái pass trong hệ thống, đúng => change password
    @PostMapping("/users/{username}/change_password")
    public ResponseEntity changePassword(@PathVariable String username, @RequestBody ChangePasswordRequest request) {
//        Account checkAccount = accountService.getUserByUsername(username);
//        try {
//            if (!request.getNewPass().equals(request.getOldPass())) {
//                checkAccount.setPassword(request.getNewPass());
//                boolean checkChangePassword = accountService.changePassword(checkAccount);
//                if (checkChangePassword) {
//                    return new ResponseEntity("Change Password successful", HttpStatus.OK);
//                }
//                return new ResponseEntity("Change Password failed", HttpStatus.BAD_REQUEST);
//            } else {
//                return new ResponseEntity("New Password must be not match with Old Password", HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity("Change Password failed", HttpStatus.BAD_REQUEST);
//        }
        //viết lại
        return null;
    }

    //bỏ try catch
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
    public ResponseEntity updateProfile(@PathVariable String username, @Valid @RequestBody Account account) {
        //save: 2 việc, update với add;
        //m add 1 đối tượng mới vào:
        //Nó ko có Id thì nó tạo mới
        //Nếu nó có Id thì
//        try {
//            return new ResponseEntity(accountService.updateProfile(account), HttpStatus.OK);
//        } catch (Exception e) {
//            //?
//            return new ResponseEntity("Ban account failed", HttpStatus.BAD_REQUEST);
//        }
        Account currentAccount = accountService.getUserByUsername(username);
        if(currentAccount == null){
            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
        }
        accountService.updateProfile(account, currentAccount);
        return  null;
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
    public ResponseEntity banAccount(@PathVariable String username) {
        Account account = accountService.getUserByUsername(username);
        try {
            account.setStatus(false);
            return new ResponseEntity(accountService.banAccount(account), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Ban account failed", HttpStatus.BAD_REQUEST);
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
        Account account = accountService.getUserByUsername(username);
        try {
            account.setStatus(true);
            accountService.activeAccount(account);
            return new ResponseEntity("Active account successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Active account failed", HttpStatus.BAD_REQUEST);
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
        System.out.println("a");
        System.out.println(isAdmin(authentication));
        Account account = accountService.getUserByUsername(username);
        if (account != null) {
            return new ResponseEntity(accountService.getUserByUsername(username), HttpStatus.OK);
        }
        return new ResponseEntity("Username can not found.", HttpStatus.NOT_FOUND);
    }

    public static boolean isMySelf(String username, Authentication authentication){
        return username.equals(authentication.getName());
    }

    public static String isAdmin(Authentication authentication){
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return roles.get(0);
    }
}
