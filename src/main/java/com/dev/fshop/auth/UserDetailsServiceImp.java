package com.dev.fshop.auth;

import com.dev.fshop.entities.Account;
import com.dev.fshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = userService.getUserByUsername(s);
        if(account == null){
            throw new UsernameNotFoundException("Username or Password is incorrect!");
        }
        return new UserPrincipal(account);
    }
}
