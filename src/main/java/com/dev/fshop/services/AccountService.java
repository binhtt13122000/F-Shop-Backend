package com.dev.fshop.services;

import com.dev.fshop.entities.Account;

import java.util.List;

public interface AccountService {
    Account getUserByUsername(String id);

    String addUser(Account account, String roleId);

    Account changeStatusAccount(Account account, boolean status);

    Account updateProfile(Account currentAccount, Account newAccount);

    boolean changePassword(Account account, String hashPassword);

    List<Account> searchAccountsByParameters(String email, String role);

    List<Account> searchAccountsByParameter(String q);
}
