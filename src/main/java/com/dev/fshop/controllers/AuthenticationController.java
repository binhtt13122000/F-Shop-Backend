package com.dev.fshop.controllers;

import com.dev.fshop.auth.JwtUtils;
import com.dev.fshop.auth.SecurityConstants;
import com.dev.fshop.auth.UserDetailsServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody AuthenticateRequest request) throws Exception {
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
            return new ResponseEntity("Username or Password is incorrect", HttpStatus.UNAUTHORIZED);
        }

    }
}
