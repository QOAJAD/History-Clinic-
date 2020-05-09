package com.qoajad.backend.service.internal.authentication;

import com.qoajad.backend.model.internal.authentication.PrimitiveUserDetail;

public interface AuthenticationService {

    PrimitiveUserDetail retrieveUserDetails(final String username);
    String generateJWT(final String username);
}
