package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.authentication.PrimitiveUserDetail;

public interface AuthenticationAccessor {

    PrimitiveUserDetail retrieveUserDetails(final String username);
}
