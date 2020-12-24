package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Role;
import com.dev.fshop.repositories.UserRepository;
import com.dev.fshop.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account getUserByUsername(String username) {
        return userRepository.findAccountByUserName(username);
    }

    @Override
    public Account addUser(Account account, String roleId) {
        account.setRole(new Role(roleId, null));
        account.setStatus(true);
        String password = account.getPassword();
        account.setPassword(passwordEncoder.encode(password));
        account.setRegisteredAt(new Date());
        return userRepository.save(account);
    }

}
