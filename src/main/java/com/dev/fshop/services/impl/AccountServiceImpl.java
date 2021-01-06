package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Role;
import com.dev.fshop.repositories.UserRepository;
import com.dev.fshop.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

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
    public String addUser(Account account, String roleId) {
        System.out.println(account.toString());
        account.setRole(new Role(roleId, null));
        account.setStatus(true);
        String password = account.getPassword();
        account.setPassword(passwordEncoder.encode(password));
        account.setRegisteredAt(new Date());
        userRepository.save(account);
        return "Create new user successfully!";
    }

    @Override
    public Account updateProfile(Account currentAccount, Account newAccount) {
        currentAccount.setStatus(newAccount.isStatus());
        currentAccount.setName(newAccount.getName());
        currentAccount.setBirthDate(newAccount.getBirthDate());
        currentAccount.setPhoneNumber(newAccount.getPhoneNumber());
        currentAccount.setGender(newAccount.isGender());
        currentAccount.setCountry(newAccount.getCountry());
        currentAccount.setAddress(newAccount.getAddress());
        currentAccount.setAvatar(newAccount.getAvatar());
        return userRepository.save(currentAccount);
    }

    @Override
    public boolean changePassword(Account account, String hashPassword) {
        account.setPassword(hashPassword);
        return userRepository.save(account) != null ? true : false;
    }

    @Override
    public Page<Account> searchAccountsByParameters(String email, String role, Pageable pageable) {
        return userRepository.searchAccountsByParameters(email, role, pageable);
    }

    @Override
    public Page<Account> searchAccountsByParameter(String q, Pageable pageable) {
        return userRepository.searchAccountsByParameter(q, pageable);
    }

    @Override
    public Account changeStatusAccount(Account account, boolean status) {
        account.setStatus(status);
        return userRepository.save(account);
    }
}
