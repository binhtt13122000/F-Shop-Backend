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

    @Override
    public Account getUserByUsername(String username) {
        return userRepository.findAccountByUserName(username);
    }

    @Override
    public Account addUser(Account account, String roleId) {
        account.setRole(new Role(roleId, null));
        account.setStatus(true);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRegisteredAt(new Date());
        return userRepository.save(account);
    }

    @Override
    public Account activeAccount(Account account) {
        account.setStatus(true);
        return userRepository.save(account);
    }

    @Override
    public Account banAccount(Account account) {
        account.setStatus(false);
        return userRepository.save(account);
    }

    @Override
    public Account updateProfile(Account currentAccount, Account newAccount) {
        currentAccount.setStatus(newAccount.isStatus());
        currentAccount.setPassword(newAccount.getPassword());
        userRepository.save(currentAccount);
        return currentAccount;
    }

    @Override
    public boolean changePassword(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return userRepository.save(account) != null? true: false;
    }


}
