package com.thoughtworks.service.provider;

import com.thoughtworks.model.User;
import com.thoughtworks.service.UserService;
import com.thoughtworks.util.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationProvider.class);
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        LOGGER.info("username =>" + username);
        LOGGER.info("password =>" + password);

        User user = (User) userService.loadUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("username not found!");
        }
        if (!EncryptUtils.encryptPassword(password).equals(user.getPassword())) {
            throw new BadCredentialsException("valid password!");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}