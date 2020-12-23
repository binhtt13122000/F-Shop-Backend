package com.dev.fshop.services;

import com.dev.fshop.entities.Account;

import java.util.List;

public interface UserService {
    Account getUserByUsername(String id);
    Account addUser(Account account, String roleId);
    String validateWhenAddUser(Account account);
}
