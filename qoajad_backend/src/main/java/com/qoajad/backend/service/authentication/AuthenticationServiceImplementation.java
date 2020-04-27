package com.qoajad.backend.service.authentication;

import com.qoajad.backend.database.accessor.AuthenticationAccessor;
import com.qoajad.backend.model.authentication.PrimitiveUserDetail;
import com.qoajad.backend.service.jwt.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
@Qualifier("defaultAuthenticationService")
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final AuthenticationAccessor authenticationAccessor;
    private final JWTService jwtService;

    @Autowired
    public AuthenticationServiceImplementation(@Qualifier("defaultAuthenticationDatabaseAccessor") final AuthenticationAccessor authenticationAccessor, @Qualifier("defaultJwtService") final JWTService jwtService) {
        this.authenticationAccessor = authenticationAccessor;
        this.jwtService = jwtService;
    }

    @Override
    public PrimitiveUserDetail retrieveUserDetails(String username) {
        return authenticationAccessor.retrieveUserDetails(username);
    }

    @Override
    public String generateJWT(String username) {
        return jwtService.generateJWT(username);
    }
}
