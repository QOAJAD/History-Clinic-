package com.qoajad.backend.service.authentication;

import com.qoajad.backend.model.authentication.PrimitiveUserDetail;

public interface AuthenticationService {

    PrimitiveUserDetail retrieveUserDetails(final String username);
    String generateJWT(final String username);
}
