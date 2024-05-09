package com.usermanagement.security.provider;

import com.usermanagement.models.UserModel;
import com.usermanagement.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserModel userModel = userService.getByUserName(username);
        if(userModel!=null){
            GrantedAuthority authority =new SimpleGrantedAuthority(userModel.getRole().toString());
            User user = new User(userModel.getUserName(),userModel.getPasswd(), Set.of(authority));
            //TODO passwordEncoder.matches(password, userDetails.getPassword()) password db şifreledikten sonra burayı değiştir
            if (user.getPassword().equals(password)) {
                return new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
            }
        }
        throw new UsernameNotFoundException("User not found or password incorrect.") {};
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
