package com.dev.fshop.services;

import com.dev.fshop.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    Account getUserByUsername(String username);

    String addUser(Account account, String roleId);

    Account changeStatusAccount(Account account);

    Account updateProfile(Account currentAccount, Account newAccount);

    boolean changePassword(Account account, String hashPassword);

    Page<Account> searchAccountsByParameters(int status, String email, String role, Pageable pageable);

    Page<Account> searchAccountsByParameter(String q, Pageable pageable);
}
