package com.qoaj.qoajbackend.unit.model.authentication;

import com.qoajad.backend.model.authentication.PrimitiveUserDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationUnitTest {

    @Test
    public void testPrimitiveUserDetailConstructorAssignsLocalFieldsCorrectly() {
        final String username = "juan.2114@hotmail.com";
        final String password = "BurBon48";

        final PrimitiveUserDetail primitiveUserDetail = new PrimitiveUserDetail(username, password);
        Assert.assertEquals(primitiveUserDetail.getUsername(), username);
        Assert.assertEquals(primitiveUserDetail.getPassword(), password);
    }
}
