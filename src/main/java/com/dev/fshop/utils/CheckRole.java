package com.dev.fshop.utils;

import org.springframework.security.core.Authentication;

public class CheckRole {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String SELLER = "ROLE_SELLER";

    public static boolean checkRoleAdmin(Authentication authentication) {
        if((authentication.getAuthorities().stream()).allMatch(r -> r.getAuthority().equals(ADMIN)))
            return true;
        return false;
    }

    public static boolean checkRoleUser(Authentication authentication) {
        if((authentication.getAuthorities().stream()).allMatch(r -> r.getAuthority().equals(USER)))
            return true;
        return false;
    }

    public static boolean checkRoleSeller(Authentication authentication) {
        if((authentication.getAuthorities().stream()).allMatch(r -> r.getAuthority().equals(SELLER)))
            return true;
        return false;
    }
}
