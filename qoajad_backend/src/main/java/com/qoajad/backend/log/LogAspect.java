package com.qoajad.backend.log;

import com.google.gson.Gson;
import com.qoajad.backend.model.log.AuthenticationLog;
import com.qoajad.backend.model.log.Log;
import com.qoajad.backend.service.log.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    @Autowired
    public LogAspect(@Qualifier("asynchronousLogService") final LogService logService) {
        this.logService = logService;
    }


    @Pointcut("execution (* com.qoajad.backend.controller.AuthenticationController.authenticate(..))")
    private void userLogin() {}

    @AfterReturning(pointcut = "userLogin()", returning = "response")
    public void logUserLogin(JoinPoint joinPoint, ResponseEntity<String> response) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        AuthenticationLog authenticationLog = new AuthenticationLog(joinPoint.getArgs()[0].toString());
        Log log = new Log(-1, -1, response.getStatusCode().name(), new Date(), request.getRemoteAddr(),
                new Gson().toJson(authenticationLog), request.getMethod());
        logService.logUserAuthentication(log);
    }
}
