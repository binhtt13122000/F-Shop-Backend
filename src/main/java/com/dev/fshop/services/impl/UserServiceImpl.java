package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Account;
import com.dev.fshop.repositories.UserRepository;
import com.dev.fshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public Account getUserByUsername(String username) {
        return userRepository.findAccountByUserName(username).orElse(null);
    }

}
