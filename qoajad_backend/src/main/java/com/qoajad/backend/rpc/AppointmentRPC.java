package com.qoajad.backend.rpc;

import com.qoajad.backend.model.external.appointment.Appointment;
import com.qoajad.backend.model.external.appointment.ConsultingRoom;
import com.qoajad.backend.model.external.appointment.CreateAppointment;
import com.qoajad.backend.model.external.response.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "AppointmentService", url = "http://thawing-stream-48846.herokuapp.com")
@Qualifier("defaultAppointmentRPC")
public interface AppointmentRPC {

    @RequestMapping(value = "/horarios/{healthProviderInstituteName}/{specializationName}", method = RequestMethod.GET)
    List<ConsultingRoom> findAvailableAppointments(@PathVariable("healthProviderInstituteName") String healthProviderInstituteName, @PathVariable("specializationName") String specializationName);

    @RequestMapping(value = "/horarios/", method = RequestMethod.POST)
    Response attemptToCreateAppointment(@RequestBody final CreateAppointment createAppointment);

    @RequestMapping(value = "/horarios/{appointmentId}", method = RequestMethod.DELETE)
    Response attemptToDeleteAppointment(@PathVariable("appointmentId") final int appointmentId);

    @RequestMapping(value = "/horarios/{userDocument}", method = RequestMethod.GET)
    List<Appointment> findUserAppointments(@PathVariable("userDocument") int userDocument);
}
