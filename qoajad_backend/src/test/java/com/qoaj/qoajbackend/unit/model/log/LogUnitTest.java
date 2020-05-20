package com.qoaj.qoajbackend.unit.model.log;

import com.qoajad.backend.model.internal.log.AuthenticationLog;
import com.qoajad.backend.model.internal.log.LogCreate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class LogUnitTest {

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException1() {
        new LogCreate("", null, new Date(), "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException2() {
        new LogCreate("", "OK", null, "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException3() {
        new LogCreate("", "OK", new Date(), null,
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException4() {
        new LogCreate("", "OK", new Date(), "0:0:0:0:0:0:0:1",
                null, "GET");
    }

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException5() {
        new LogCreate("", "OK", new Date(), "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), null);
    }

    @Test(expected = IllegalStateException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException6() {
        new LogCreate("", "OK", new Date(), "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }

    @Test(expected = IllegalStateException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException7() {
        new LogCreate("", "OK", new Date(), "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }


    @Test(expected = NullPointerException.class)
    public void testAuthenticationLogConstructorFieldsAreInvalidAndThrowsException1() {
        new AuthenticationLog(null);
    }

    @Test
    public void testLogConstructorAssignsLocalFieldsCorrectly() {
        final int id = 1;
        final String activeUsername = "";
        final String state = "OK";
        final Date requestDate = new Date();
        final String ip = "0:0:0:0:0:0:0:1";
        final Object data = new AuthenticationLog("juan@hotmail.com");
        final String requestType = "GET";
        final String eventType = data.getClass().getSimpleName();

        final LogCreate logCreate = new LogCreate(activeUsername, state, requestDate, ip, data, requestType);
        Assert.assertEquals(activeUsername, logCreate.getActiveUsername());
        Assert.assertEquals(state, logCreate.getState());
        Assert.assertEquals(requestDate, logCreate.getRequestDate());
        Assert.assertEquals(ip, logCreate.getIp());
        Assert.assertEquals(data, logCreate.getData());
        Assert.assertEquals(requestType, logCreate.getRequestType());
        Assert.assertEquals(eventType, logCreate.getEventType());
    }

    @Test
    public void testAuthenticationLogConstructorAssignsLocalFieldsCorrectly() {
        final String username = "juan@hotmail.com";

        final AuthenticationLog authenticationLog = new AuthenticationLog(username);
        Assert.assertEquals(username, authenticationLog.getUsername());
    }

}
