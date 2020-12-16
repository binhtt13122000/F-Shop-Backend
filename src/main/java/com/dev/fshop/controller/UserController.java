package com.dev.fshop.controller;

import com.dev.fshop.entity.CustomerEntity;
import com.dev.fshop.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class UserController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping(path = "/users")
    public ResponseEntity<CustomerEntity> login(@RequestBody CustomerEntity customerEntity){
        return  ResponseEntity.ok().body(userServiceInterface.loginByUserIdAndPass(customerEntity.getUserId(), customerEntity.getPassword()));
    }

    @PostMapping(path = "/users")
    public ResponseEntity<CustomerEntity> registerAccount(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok().body(userServiceInterface.registerAccountUser(customerEntity));
    }

    @GetMapping(path = "/users")
    public ResponseEntity<CustomerEntity> getUserByUserId(@RequestParam(name = "userId")String userId) {
        return ResponseEntity.ok().body(userServiceInterface.getUserById(userId));
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<CustomerEntity>> getAllUsers() {
        return ResponseEntity.ok().body(userServiceInterface.getAllUsers());
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<CustomerEntity>> getUsersByName(@RequestParam(name = "userName") String userName) {
        return ResponseEntity.ok().body(userServiceInterface.searchCustomersByName(userName));
    }

    @PatchMapping(path = "/users")
    public ResponseEntity<CustomerEntity> changePassword(@RequestParam(name = "userId")String userId, @RequestParam(name = "newPass") String newPass,
                                         @RequestParam(name = "oldPass") String oldPass) {
        return ResponseEntity.ok().body(userServiceInterface.changePassword(userId,newPass,oldPass));
    }

    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<CustomerEntity> updateProfileUser(@PathVariable String userId, @RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok().body(userServiceInterface.updateProfileUser(userId, customerEntity));
    }

    @DeleteMapping(path = "/users/{userId}")
    public boolean removeAccountUserExisted(@PathVariable String userId) {
        return userServiceInterface.removeAccountUser(userId);
    }

}
