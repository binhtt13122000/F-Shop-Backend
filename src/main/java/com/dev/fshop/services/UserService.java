package com.dev.fshop.services;

import com.dev.fshop.entities.CustomerEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    //Get Methods
    public CustomerEntity loginByUserIdAndPass(String userId, String password);
    public List<CustomerEntity> searchCustomersByName(String userName);
    public List<CustomerEntity> getAllUsers();
    public CustomerEntity getUserById(String userId);

    //Post Methods
    public CustomerEntity registerAccountUser(CustomerEntity customerEntity);

    //Put Methods
    public CustomerEntity changePassword(String userId, String newPassword, String oldPassword);
    public CustomerEntity updateProfileUser(String userId, CustomerEntity customerEntity);


    //Delete Methods
    public boolean removeAccountUser(String userId);



}
