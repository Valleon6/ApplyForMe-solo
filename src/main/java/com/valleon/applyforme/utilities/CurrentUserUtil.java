package com.valleon.applyforme.utilities;

import com.valleon.applyforme.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtil {

    public static UserDetailsImpl getCurrentUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        System.out.println(principal);
        if(principal instanceof UserDetails){
            System.out.println((UserDetails) context.getAuthentication().getPrincipal());
            return (UserDetailsImpl) context.getAuthentication().getPrincipal();
        }else{
            return null;
        }
    }
}
