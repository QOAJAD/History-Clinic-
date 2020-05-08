package com.qoaj.qoajbackend.unit.model.log;

import com.google.gson.Gson;
import com.qoajad.backend.model.log.AuthenticationLog;
import com.qoajad.backend.model.log.Log;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class LogUnitTest {

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException1() {
        new Log(1, 0, null, new Date(), "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException2() {
        new Log(1, 0, "OK", null, "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException3() {
        new Log(1, 0, "OK", new Date(), null,
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException4() {
        new Log(1, 0, "OK", new Date(), "0:0:0:0:0:0:0:1",
                null, "GET");
    }

    @Test(expected = NullPointerException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException5() {
        new Log(1, 0, "OK", new Date(), "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), null);
    }

    @Test(expected = IllegalStateException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException6() {
        new Log(0, 0, "OK", new Date(), "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }

    @Test(expected = IllegalStateException.class)
    public void testLogConstructorFieldsAreInvalidAndThrowsException7() {
        new Log(-1, 0, "OK", new Date(), "0:0:0:0:0:0:0:1",
                new AuthenticationLog("juan@hotmail.com"), "GET");
    }


    @Test(expected = NullPointerException.class)
    public void testAuthenticationLogConstructorFieldsAreInvalidAndThrowsException1() {
        new AuthenticationLog(null);
    }

    @Test
    public void testLogConstructorAssignsLocalFieldsCorrectly() {
        final int id = 1;
        final int activeUserId = 0;
        final String state = "OK";
        final Date requestDate = new Date();
        final String ip = "0:0:0:0:0:0:0:1";
        final Object data = new AuthenticationLog("juan@hotmail.com");
        final String requestType = "GET";
        final String eventType = data.getClass().getSimpleName();

        final Log log = new Log(id, activeUserId, state, requestDate, ip, data, requestType);
        Assert.assertEquals(id, log.getId());
        Assert.assertEquals(activeUserId, log.getActiveUserId());
        Assert.assertEquals(state, log.getState());
        Assert.assertEquals(requestDate, log.getRequestDate());
        Assert.assertEquals(ip, log.getIp());
        Assert.assertEquals(data, log.getData());
        Assert.assertEquals(requestType, log.getRequestType());
        Assert.assertEquals(eventType, log.getEventType());
    }

    @Test
    public void testAuthenticationLogConstructorAssignsLocalFieldsCorrectly() {
        final String username = "juan@hotmail.com";

        final AuthenticationLog authenticationLog = new AuthenticationLog(username);
        Assert.assertEquals(username, authenticationLog.getUsername());
    }

}
