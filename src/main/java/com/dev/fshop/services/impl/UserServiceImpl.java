package com.dev.fshop.services.impl;

import com.dev.fshop.entities.CustomerEntity;
import com.dev.fshop.repositories.UserRepository;
import com.dev.fshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public CustomerEntity loginByUserIdAndPass(String userId, String password) {
////        return userRepository.checkLoginByIdPassword(userId, password);
//        return null;
//    }
//
//    @Override
//    public List<CustomerEntity> searchCustomersByName(String userName) {
////        return userRepository.findByCustomerName(userName);
//        return null;
//    }
//
//    @Override
//    public List<CustomerEntity> getAllUsers() {
//        return null;
////        return userRepository.findAll();
//    }
//
//    @Override
//    public CustomerEntity getUserById(String userId) {
//        return userRepository.findById(userId).orElse(null);
//    }
//
//    @Override
//    public CustomerEntity registerAccountUser(CustomerEntity customerEntity) {
////        CustomerEntity checkExisted = userRepository.findById(customerEntity.getUserId()).orElse(null);
////        if(checkExisted == null) {
////
////        }
////        return userRepository.insertCustomerWithEntityManager(customerEntity);
//        return null;
//    }
//
//    @Override
//    public CustomerEntity changePassword(String userId, String newPassword, String oldPassword) {
//                CustomerEntity checkExisted = userRepository.findById(userId).orElse(null);
////        if(checkExisted == null) {
////
////        }else {
////            if(!newPassword.equals(oldPassword)) {
////
////            }else {
////
////            }
////        }
////        return userRepository.updatePassword(userId, newPassword);
//        return null;
//    }
//
//    @Override
//    public CustomerEntity updateProfileUser(String userId, CustomerEntity customerEntity) {
//        //        CustomerEntity checkExisted = userRepository.findById(customerEntity.getUserId()).orElse(null);
////        if(checkExisted == null) {
////
////        }
////        return userRepository.updateProfile(customerEntity);
//        return null;
//    }
//
//    @Override
//    public boolean removeAccountUser(String userId) {
//                CustomerEntity checkExisted = userRepository.findById(userId).orElse(null);
//        if(checkExisted == null) {
//            return false;
//        }else {
////            userRepository.deleteAccount(userId);
//            return true;
//        }
//    }
}
