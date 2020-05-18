package com.qoajad.backend.log;

import com.qoajad.backend.model.external.appointment.Appointment;
import com.qoajad.backend.model.external.appointment.ConsultingRoom;
import com.qoajad.backend.model.external.appointment.CreateAppointment;
import com.qoajad.backend.model.external.appointment.UpdateAppointment;
import com.qoajad.backend.model.external.health.HealthProviderInstitute;
import com.qoajad.backend.model.external.specialty.Specialty;
import com.qoajad.backend.model.internal.log.AuthenticationLog;
import com.qoajad.backend.model.internal.log.DeleteAppointmentLog;
import com.qoajad.backend.model.internal.log.LogCreate;
import com.qoajad.backend.model.internal.log.UpdateAppointmentLog;
import com.qoajad.backend.service.external.appointment.AppointmentService;
import com.qoajad.backend.service.internal.log.WritableLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class LogAspect {

    private final WritableLogService writableLogService;
    private final AppointmentService appointmentService;
    private HttpServletRequest request;

    @Autowired
    public LogAspect(@Qualifier("asynchronousWritableLogService") final WritableLogService writableLogService,
                     @Qualifier("defaultAppointmentService") final AppointmentService appointmentService) {
        this.writableLogService = writableLogService;
        this.appointmentService = appointmentService;
    }

    @Pointcut("execution (* com.qoajad.backend.controller..*(..))")
    private void controllerPointcut() {}

    @Pointcut("execution (* com.qoajad.backend.controller.AuthenticationController.authenticate(..))")
    private void userLoginPointcut() {}

    @Pointcut("execution (* com.qoajad.backend.controller.AppointmentController.findAvailableAppointments(..))")
    private void findAvailableAppointmentsPointcut() {}

    @Pointcut("execution (* com.qoajad.backend.controller.AppointmentController.findUserAppointments(..))")
    private void findUserAppointmentsPointcut() {}

    @Pointcut("execution (* com.qoajad.backend.controller.AppointmentController.createAppointment(..))")
    private void createAppointmentPointcut() {}

    @Pointcut("execution (* com.qoajad.backend.controller.AppointmentController.updateAppointment(..))")
    private void updateAppointmentPointcut() {}

    @Pointcut("execution (* com.qoajad.backend.controller.AppointmentController.deleteAppointment(..))")
    private void deleteAppointmentPointcut() {}

    @Pointcut("execution (* com.qoajad.backend.controller.HealthProviderInstituteController.retrieveAllHPI(..))")
    private void retrieveAllHPIPointcut() {}

    @Pointcut("execution (* com.qoajad.backend.controller.SpecialtyController.retrieveAllSpecialties(..))")
    private void retrieveAllSpecialtiesPointcut() {}

    @After("controllerPointcut()")
    public void logController() {
        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    @AfterReturning(pointcut = "userLoginPointcut()", returning = "response")
    public void logUserLogin(JoinPoint joinPoint, ResponseEntity<String> response) {
        final AuthenticationLog authenticationLog = new AuthenticationLog(joinPoint.getArgs()[0].toString());
        final LogCreate logCreate = new LogCreate("", response.getStatusCode().name(), new Date(),
                request.getRemoteAddr(), authenticationLog, request.getMethod());
        writableLogService.log(logCreate);
        request = null;
    }

    @AfterReturning(pointcut = "findAvailableAppointmentsPointcut()", returning = "response")
    public void logFindAvailableAppointments(JoinPoint joinPoint, ResponseEntity<List<ConsultingRoom>> response) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final LogCreate logCreate = new LogCreate(currentUser.getUsername(), response.getStatusCode().name(), new Date(),
                request.getRemoteAddr(), response.getBody(), request.getMethod());
        writableLogService.log(logCreate);
        request = null;

    }

    @AfterReturning(pointcut = "findUserAppointmentsPointcut()", returning = "response")
    public void logFindUserAppointments(JoinPoint joinPoint, ResponseEntity<List<Appointment>> response) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final LogCreate logCreate = new LogCreate(currentUser.getUsername(), response.getStatusCode().name(), new Date(),
                request.getRemoteAddr(), response.getBody(), request.getMethod());
        writableLogService.log(logCreate);
        request = null;
    }

    @AfterReturning(pointcut = "createAppointmentPointcut()", returning = "response")
    public void logCreateAppointment(JoinPoint joinPoint, ResponseEntity<String> response) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CreateAppointment createAppointment = (CreateAppointment) joinPoint.getArgs()[0];
        final LogCreate logCreate = new LogCreate(currentUser.getUsername(), response.getStatusCode().name(), new Date(),
                request.getRemoteAddr(), createAppointment, request.getMethod());
        writableLogService.log(logCreate);
        request = null;
    }

    @Around("updateAppointmentPointcut()")
    public void logUpdateAppointment(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        final UpdateAppointment updateAppointment = (UpdateAppointment) args[0];
        Appointment oldAppointment;
        Appointment newAppointment;
        oldAppointment = appointmentService.findUserAppointment(updateAppointment.getId(),
                updateAppointment.getPatientDocument());
        ResponseEntity<String> response = (ResponseEntity<String>) proceedingJoinPoint.proceed(args);
        newAppointment = appointmentService.findUserAppointment(updateAppointment.getId(),
                updateAppointment.getPatientDocument());
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final UpdateAppointmentLog updateAppointmentLog = new UpdateAppointmentLog(oldAppointment, newAppointment);
        final LogCreate logCreate = new LogCreate(currentUser.getUsername(), response.getStatusCode().name(), new Date(),
                request.getRemoteAddr(), updateAppointmentLog, request.getMethod());
        writableLogService.log(logCreate);
        request = null;
    }

    @AfterReturning(pointcut = "deleteAppointmentPointcut()", returning = "response")
    public void logDeleteAppointment(JoinPoint joinPoint, ResponseEntity<String> response) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DeleteAppointmentLog deleteAppointmentLog = new DeleteAppointmentLog((int) joinPoint.getArgs()[0]);
        final LogCreate logCreate = new LogCreate(currentUser.getUsername(), response.getStatusCode().name(), new Date(),
                request.getRemoteAddr(), deleteAppointmentLog, request.getMethod());
        writableLogService.log(logCreate);
    }

    @AfterReturning(pointcut = "retrieveAllHPIPointcut()", returning = "response")
    public void logRetrieveAllHPI(JoinPoint joinPoint, ResponseEntity<List<HealthProviderInstitute>> response) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final LogCreate logCreate = new LogCreate(currentUser.getUsername(), response.getStatusCode().name(), new Date(),
                request.getRemoteAddr(), response.getBody(), request.getMethod());
        writableLogService.log(logCreate);
    }

    @AfterReturning(pointcut = "retrieveAllSpecialtiesPointcut()", returning = "response")
    public void logRetrieveAllSpecialties(JoinPoint joinPoint, ResponseEntity<List<Specialty>> response) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final LogCreate logCreate = new LogCreate(currentUser.getUsername(), response.getStatusCode().name(), new Date(),
                request.getRemoteAddr(), response.getBody(), request.getMethod());
        writableLogService.log(logCreate);
    }
}
