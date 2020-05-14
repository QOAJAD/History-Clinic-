package com.qoajad.backend.controller;

import com.qoajad.backend.model.external.appointment.Appointment;
import com.qoajad.backend.model.external.appointment.ConsultingRoom;
import com.qoajad.backend.model.external.appointment.CreateAppointment;
import com.qoajad.backend.model.external.appointment.UpdateAppointment;
import com.qoajad.backend.service.external.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<List<ConsultingRoom>> findAvailableAppointments(@PathVariable String hpiName,
                                                                          @PathVariable String specialtyName) {
        ResponseEntity<List<ConsultingRoom>> response;
        try {
            response = new ResponseEntity<>(
                    appointmentService.findAvailableAppointments(hpiName, specialtyName),
                    HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/appointment/all_user_appointments/{userDocument}", method = RequestMethod.GET)
    public ResponseEntity<List<Appointment>> findUserAppointments(@PathVariable int userDocument) {
        ResponseEntity<List<Appointment>> response;
        try {
            response = new ResponseEntity<>(
                    appointmentService.findUserAppointments(userDocument),
                    HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/appointment/create", method = RequestMethod.POST)
    public ResponseEntity<String> createAppointment(@RequestBody CreateAppointment createAppointment) {
        ResponseEntity<String> response;
        try {
            appointmentService.attemptToCreateAppointment(createAppointment);
            response = new ResponseEntity<>("Appointment created successfully.", HttpStatus.CREATED);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/appointment/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAppointment(@RequestBody UpdateAppointment updateAppointment) {
        ResponseEntity<String> response;
        try {
            appointmentService.attemptToUpdateAppointment(updateAppointment);
            //TODO: Add the number of rows changed to reflect if it was updated or not.
            response = new ResponseEntity<>("rows changed.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/appointment/delete/{appointmentId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAppointment(@PathVariable int appointmentId) {
        ResponseEntity<String> response;
        try {
            appointmentService.attemptToDeleteAppointment(appointmentId);
            //TODO: Add the number of rows changed to reflect if it was deleted or not.
            response = new ResponseEntity<>("rows changed.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

   // TODO(Juan, AntonioYu): This has to be redone based on the api requirements. The services are done, so we just need to fulfill the service with the parameters.
}
