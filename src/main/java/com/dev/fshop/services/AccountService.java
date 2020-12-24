package com.dev.fshop.services;

import com.dev.fshop.entities.Account;
import com.dev.fshop.validation.FieldValueExists;

import java.util.List;

public interface AccountService extends FieldValueExists {
    Account getUserByUsername(String id);
    Account addUser(Account account, String roleId);
    String validateWhenAddUser(Account account);
}
