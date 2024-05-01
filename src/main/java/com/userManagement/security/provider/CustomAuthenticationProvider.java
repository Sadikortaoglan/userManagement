package com.userManagement.security.provider;

import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userService.getByUserName(username);
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority("ROLE_USER");
        return new UsernamePasswordAuthenticationToken(
                "sadik", "12345", Set.of(grantedAuthority));
       /* if (userDetails != null && userDetails.getPassword().equals(password)) {

        } else {
            throw new UsernameNotFoundException("User not found or password incorrect.");
        }*/
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}