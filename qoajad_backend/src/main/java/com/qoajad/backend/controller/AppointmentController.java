package com.qoajad.backend.controller;

import com.qoajad.backend.model.appointment.Appointment;
import com.qoajad.backend.model.appointment.CreateAppointment;
import com.qoajad.backend.model.appointment.UpdateAppointment;
import com.qoajad.backend.model.user.User;
import com.qoajad.backend.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(@Qualifier("defaultAppointmentService") final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/appointment/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Appointment> findAppointment(@PathVariable("id") final int id) {
        ResponseEntity<Appointment> response;
        try {
            response = new ResponseEntity<>(appointmentService.findAppointment(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(value = "/appointment/create", method = RequestMethod.POST)
    public ResponseEntity<String> createAppointment(@RequestBody CreateAppointment createAppointment) {
        ResponseEntity<String> response;
        try {
            appointmentService.createAppointment(createAppointment);
            response = new ResponseEntity<>("Appointment created successfully.", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/appointment/update", method = RequestMethod.POST)
    public ResponseEntity<String> updateAppointment(@RequestBody UpdateAppointment updateAppointment) {
        ResponseEntity<String> response;
        try {
            appointmentService.updateAppointment(updateAppointment);
            response = new ResponseEntity<>("Appointment updated successfully.", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }
}
