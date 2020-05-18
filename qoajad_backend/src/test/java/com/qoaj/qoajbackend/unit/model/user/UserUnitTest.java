package com.qoaj.qoajbackend.unit.model.user;

import com.qoajad.backend.model.internal.user.UpdateUser;
import com.qoajad.backend.model.internal.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserUnitTest {

    @Test(expected = IllegalStateException.class)
    public void testUpdateUserConstructorFieldsAreInvalidAndThrowsException() {
        new UpdateUser("username", "password", 1144099495);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateUserConstructorFieldsAreInvalidAndThrowsException1() {
        new UpdateUser(null, "password", 16748051);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateUserConstructorFieldsAreInvalidAndThrowsException2() {
        new UpdateUser("username", null, 16748051);
    }

    @Test(expected = IllegalStateException.class)
    public void testUpdateUserConstructorFieldsAreInvalidAndThrowsException3() {
        new UpdateUser("username", "password", -1);
    }

    @Test
    public void testUpdateUserConstructorAssignsLocalFieldsCorrectly() {
        final String username = "juan.2114@hotmail.com";
        final String password = "BurBon48";
        final int document = 16784098;

        final UpdateUser updateUser = new UpdateUser(username, password, document);
        Assert.assertEquals(updateUser.getUsername(), username);
        Assert.assertEquals(updateUser.getPassword(), password);
        Assert.assertEquals(updateUser.getDocument(), document);
    }

    @Test
    public void testUserConstructorAssignsLocalFieldsCorrectly() {
        final int id = 4785;
        final String username = "roberto97@gmail.com";
        final String password = "bertoP45";
        final int document = 66808451;

        final User user = new User(id, username, password, document);
        Assert.assertEquals(user.getId(), id);
        Assert.assertEquals(user.getUsername(), username);
        Assert.assertEquals(user.getPassword(), password);
        Assert.assertEquals(user.getDocument(), document);
    }
}
