package com.dev.fshop.auth;

import com.dev.fshop.entities.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Account account;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return account.getUserName();
    }

    @Override
    public String getUsername() {
        return account.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.isStatus();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.isStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.isStatus();
    }

    @Override
    public boolean isEnabled() {
        return account.isStatus();
    }
}
