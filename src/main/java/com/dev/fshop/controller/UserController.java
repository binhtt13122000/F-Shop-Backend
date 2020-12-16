package com.dev.fshop.controller;

import com.dev.fshop.entity.CustomerEntity;
import com.dev.fshop.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class UserController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping(path = "/users")
    public CustomerEntity login(@RequestBody CustomerEntity customerEntity){
        return  userServiceInterface.loginByUserIdAndPass(customerEntity.getUserId(), customerEntity.getPassword());
    }

    @PostMapping(path = "/users")
    public CustomerEntity registerAccount(@RequestBody CustomerEntity customerEntity) {
        return userServiceInterface.registerAccountUser(customerEntity);
    }

    @GetMapping(path = "/users")
    public CustomerEntity getUserByUserId(@RequestParam(name = "userId")String userId) {
        return userServiceInterface.getUserById(userId);
    }

    @GetMapping(path = "/users")
    public List<CustomerEntity> getAllUsers() {
        return userServiceInterface.getAllUsers();
    }

    @GetMapping(path = "/users")
    public List<CustomerEntity> getUsersByName(@RequestParam(name = "userName") String userName) {
        return userServiceInterface.searchCustomersByName(userName);
    }

    @PatchMapping(path = "/users")
    public CustomerEntity changePassword(@RequestParam(name = "userId")String userId, @RequestParam(name = "newPass") String newPass,
                                         @RequestParam(name = "oldPass") String oldPass) {
        return userServiceInterface.changePassword(userId,newPass,oldPass);
    }

    @PutMapping(path = "/users/{userId}")
    public CustomerEntity updateProfileUser(@PathVariable String userId, @RequestBody CustomerEntity customerEntity) {
        return userServiceInterface.updateProfileUser(userId, customerEntity);
    }

    @DeleteMapping(path = "/users/{userId}")
    public boolean removeAccountUserExisted(@PathVariable String userId) {
        return userServiceInterface.removeAccountUser(userId);
    }

}
