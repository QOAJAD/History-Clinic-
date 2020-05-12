package com.qoajad.backend.log;

import com.qoajad.backend.model.log.AuthenticationLog;
import com.qoajad.backend.model.log.Log;
import com.qoajad.backend.service.log.WritableLogService;
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

    private final WritableLogService writableLogService;

    @Autowired
    public LogAspect(@Qualifier("asynchronousWritableLogService") final WritableLogService writableLogService) {
        this.writableLogService = writableLogService;
    }

    @Pointcut("execution (* com.qoajad.backend.controller.AuthenticationController.authenticate(..))")
    private void userLoginPointcut() {}

    @AfterReturning(pointcut = "userLoginPointcut()", returning = "response")
    public void logUserLogin(JoinPoint joinPoint, ResponseEntity<String> response) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final AuthenticationLog authenticationLog = new AuthenticationLog(joinPoint.getArgs()[0].toString());
        // TODO(Juan, AntonioYU): The Log model should be replaced with a LogCreation model because at the creation point we don't know the log id.
        // Instead, the Log model should be used to retrieve a model.
        final int temporalLogId = 1;
        final Log log = new Log(temporalLogId, -1, response.getStatusCode().name(), new Date(), request.getRemoteAddr(),
                authenticationLog, request.getMethod());
        writableLogService.log(log);
    }
}
