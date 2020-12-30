package com.dev.fshop.controllers;

import com.dev.fshop.auth.JwtUtils;
import com.dev.fshop.auth.SecurityConstants;
import com.dev.fshop.auth.UserDetailsServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Data
class AuthenticateRequest {
    private String username;
    private String password;
}
@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImp userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(description = "Login", responses = {
            @ApiResponse(
                    description = "Login Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Login Successfully!",
                                    value = "Login Successfully!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Username or password is incorrect",
                    responseCode = "401",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Username or password is incorrect",
                                    value = "Username or password is incorrect"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody AuthenticateRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails user = userDetailService.loadUserByUsername(request.getUsername());
            String token = jwtUtils.generateToken(user);
            HttpHeaders headers = new HttpHeaders();
            String cookieToken = SecurityConstants.TOKEN_HEADER + "=" + token;
            headers.add("Set-Cookie",
                    cookieToken + "; HttpOnly; SameSite=None; Max-Age=864000");
            return new ResponseEntity("Login Successfully!",headers, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity("Username or Password is incorrect", HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(description = "Logout", responses = {
            @ApiResponse(
                    description = "Logout Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Logout Successfully!",
                                    value = "Logout Successfully!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Access denied",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Access denied",
                                    value = "Access denied"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/users/{studentId}/logout")
    public ResponseEntity logout(@PathVariable("studentId") String studentId, Authentication authentication){
        if(!authentication.getName().equals(studentId)){
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity("Logout successfully", HttpStatus.OK);
    }
}