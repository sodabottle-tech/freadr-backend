package com.sodabottle.freadr.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private UserTokenRepo users;

    public CustomUserDetailsService(UserTokenRepo users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return this.users.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + userId + " not found"));
    }
}
