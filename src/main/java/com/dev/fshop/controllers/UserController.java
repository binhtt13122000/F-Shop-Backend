package com.dev.fshop.controllers;

import com.dev.fshop.entities.CustomerEntity;
import com.dev.fshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/users/login")
    public ResponseEntity<CustomerEntity> login(@RequestBody CustomerEntity customerEntity){
        return  ResponseEntity.ok().body(userService.loginByUserIdAndPass(customerEntity.getUserId(), customerEntity.getPassword()));
    }

    @PostMapping(path = "/users")
    public ResponseEntity<CustomerEntity> registerAccount(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok().body(userService.registerAccountUser(customerEntity));
    }

    @GetMapping(path = "/users")
    public ResponseEntity<CustomerEntity> findUser(
            @RequestParam Optional<String> userId,
            @RequestParam Optional<String> userName) {
        return null;
    }


    @PatchMapping(path = "/users")
    public ResponseEntity<CustomerEntity> changePassword(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String newPass = request.getParameter("newPass");
        String oldPass = request.getParameter("oldPass");
        return ResponseEntity.ok().body(userService.changePassword(userId,newPass,oldPass));
    }

    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<CustomerEntity> updateProfileUser(@PathVariable String userId, @RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok().body(userService.updateProfileUser(userId, customerEntity));
    }

    @DeleteMapping(path = "/users/{userId}")
    public boolean removeAccountUserExisted(@PathVariable String userId) {
        return userService.removeAccountUser(userId);
    }

}
