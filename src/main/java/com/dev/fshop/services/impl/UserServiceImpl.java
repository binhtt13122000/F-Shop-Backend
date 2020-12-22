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
    @Override
    public Account loginByUserIdAndPass(String userId, String password) {
//        return userRepository.checkLoginByIdPassword(userId, password);
        return null;

    }

    @Override
    public List<Account> searchCustomersByName(String userName) {
        return null;

//        return  userRepository.findByName(userName);
    }

    @Override
    public List<Account> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Account getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public Account registerAccountUser(Account account) {
//        CustomerEntity checkExisted = userRepository.findById(customerEntity.getUserId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return  userRepository.save(account);
    }

    @Override
    public Account changePassword(String userId, String newPassword, String oldPassword) {
                Account checkExisted = userRepository.findById(userId).orElse(null);
//        if(checkExisted == null) {
//
//        }else {
//            if(!newPassword.equals(oldPassword)) {
//
//            }else {
//
//            }
//        }
//        return userRepository.updatePassword(userId, newPassword);
        return null;

    }

    @Override
    public Account updateProfileUser(String userId, Account account) {
        //        CustomerEntity checkExisted = userRepository.findById(customerEntity.getUserId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return userRepository.save(account);
    }

    @Override
    public boolean removeAccountUser(String userId) {
                Account checkExisted = userRepository.findById(userId).orElse(null);
        if(checkExisted == null) {
            return false;
        }else {
            userRepository.deleteById(userId);
            return true;
        }
    }
}
