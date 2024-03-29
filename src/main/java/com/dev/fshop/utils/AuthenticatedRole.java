package com.dev.fshop.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticatedRole {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String SELLER = "ROLE_SELLER";

    public static boolean isMySelf(String userName, Authentication authentication) {
        if (authentication != null) {
            if (userName != null) {
                return userName.equals(authentication.getName());
            }
            return false;
        }
        return false;
    }

    public static boolean isAdmin(Authentication authentication) {
        if (authentication != null) {
            List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.get(0).equals(ADMIN))
                return true;
            return false;
        }
        return false;
    }

    public static boolean isUser(Authentication authentication) {
        if (authentication != null) {
            List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.get(0).equals(USER))
                return true;
            return false;
        }
        return false;
    }

    public static boolean isSeller(Authentication authentication) {
        if (authentication != null) {
            List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.get(0).equals(SELLER))
                return true;
            return false;
        }
        return false;
    }
}
