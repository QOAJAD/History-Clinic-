package com.qoajad.backend.controller;

import com.qoajad.backend.model.external.eps.appointment.*;
import com.qoajad.backend.model.internal.response.Response;
import com.qoajad.backend.service.external.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(@Qualifier("defaultAppointmentService") final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/appointment/all_available_appointments/{hpiName}/{specialtyName}", method = RequestMethod.GET)
    public ResponseEntity<List<ConsultingRoom>> findAvailableAppointments(@PathVariable("hpiName") final String hpiName,
                                                                          @PathVariable("specialtyName") final String specialtyName) {
        ResponseEntity<List<ConsultingRoom>> response;
        response = new ResponseEntity<>(appointmentService.findAvailableAppointments(hpiName, specialtyName), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/appointment/all_user_appointments/{userDocument}", method = RequestMethod.GET)
    public ResponseEntity<List<Appointment>> findUserAppointments(@PathVariable("userDocument") final int userDocument) {
        ResponseEntity<List<Appointment>> response;
        response = new ResponseEntity<>(appointmentService.findUserAppointments(userDocument), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/appointment/create", method = RequestMethod.POST)
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody final CreateAppointment createAppointment) {
        ResponseEntity<CreateAppointmentResponse> response;
        try {
            CreateAppointmentResponse createAppointmentResponse = appointmentService.attemptToCreateAppointment(createAppointment);
            response = new ResponseEntity<>(createAppointmentResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/appointment/update", method = RequestMethod.PUT)
    public ResponseEntity<Response> updateAppointment(@RequestBody final UpdateAppointment updateAppointment) {
        ResponseEntity<Response> response;
        try{
            appointmentService.attemptToUpdateAppointment(updateAppointment);
            response = new ResponseEntity<>(new Response("Appointment updated."), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(new Response("An error has occurred with the EPS service."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/appointment/delete/{appointmentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteAppointment(@PathVariable("appointmentId") final int appointmentId) {
        ResponseEntity<Response> response;
        try{
            appointmentService.attemptToDeleteAppointment(appointmentId);
            response = new ResponseEntity<>(new Response("Appointment deleted."), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(new Response("An error has occurred with the EPS service."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

   // TODO(Juan, AntonioYu): This has to be redone based on the api requirements. The services are done, so we just need to fulfill the service with the parameters.
}
