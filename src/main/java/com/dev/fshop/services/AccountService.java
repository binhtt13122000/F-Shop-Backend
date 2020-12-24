package com.dev.fshop.services;

import com.dev.fshop.entities.Account;

public interface AccountService {
    Account getUserByUsername(String id);
    Account addUser(Account account, String roleId);
    Account activeAccount(Account account);
    Account banAccount(Account account);
    Account updateProfile(Account account);
    boolean changePassword(Account account);
}
