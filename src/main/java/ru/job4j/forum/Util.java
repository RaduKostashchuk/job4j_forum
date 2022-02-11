package ru.job4j.forum;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

    public static boolean checkUser(String user) {
        boolean result = false;
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUser.equals(user)) {
            result = true;
        } else {
            for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
