package com.dev.fshop.services;

import com.dev.fshop.entities.Account;

public interface AccountService {
    Account getUserByUsername(String id);
    Account addUser(Account account, String roleId);
}
