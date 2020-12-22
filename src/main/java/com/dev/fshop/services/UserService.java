package com.dev.fshop.services;

import com.dev.fshop.entities.Account;

import java.util.List;

public interface UserService {
    //Get Methods
    public Account loginByUserIdAndPass(String userId, String password);
    public List<Account> searchCustomersByName(String userName);
    public List<Account> getAllUsers();
    public Account getUserById(String userId);

    //Post Methods
    public Account registerAccountUser(Account account);

    //Put Methods
    public Account changePassword(String userId, String newPassword, String oldPassword);
    public Account updateProfileUser(String userId, Account account);


    //Delete Methods
    public boolean removeAccountUser(String userId);



}
