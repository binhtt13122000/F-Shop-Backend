package com.dev.fshop.auth;

import com.dev.fshop.entities.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Account account;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(account.getRole().getRoleName()));
    }

    @Override
    public String getUsername() {
        return account.getUserName();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        if(account.getStatus() == 1) {
            return true;
        }
            return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(account.getStatus() == 1) {
            return true;
        }
            return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(account.getStatus() == 1) {
            return true;
        }
            return false;
    }

    @Override
    public boolean isEnabled() {
        if(account.getStatus() == 1) {
            return true;
        }
        return false;
    }
}
