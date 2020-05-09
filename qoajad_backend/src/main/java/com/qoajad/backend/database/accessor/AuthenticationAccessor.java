package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.internal.authentication.PrimitiveUserDetail;

public interface AuthenticationAccessor {

    PrimitiveUserDetail retrieveUserDetails(final String username);
}
