package com.qoajad.backend.spring.configuration;

import com.qoajad.backend.model.internal.authentication.PrimitiveUserDetail;
import com.qoajad.backend.service.internal.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Qualifier("defaultUserDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final AuthenticationService authenticationService;

    public UserDetailsServiceImplementation(@Qualifier("defaultAuthenticationService") final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final PrimitiveUserDetail userDetail = authenticationService.retrieveUserDetails(username);
        if (userDetail != null) {
            return new User(userDetail.getUsername(), userDetail.getPassword(), Collections.emptyList());
        }
        return null;
    }
}
