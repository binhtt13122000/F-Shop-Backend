package com.dev.fshop.auth;

import com.dev.fshop.entities.Account;
import com.dev.fshop.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountService.getUserByUsername(s);
        if(account == null){
            throw new UsernameNotFoundException("Username or Password is incorrect!");
        }
        return new UserPrincipal(account);
    }
}
