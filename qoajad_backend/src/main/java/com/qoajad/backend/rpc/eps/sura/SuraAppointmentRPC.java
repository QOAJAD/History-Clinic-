package com.qoajad.backend.rpc.eps.sura;

import com.qoajad.backend.model.external.eps.appointment.*;
import com.qoajad.backend.model.external.eps.response.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "SuraAppointmentService", url = "http://127.0.0.1")
@Qualifier("defaultSuraAppointmentRPC")
public interface SuraAppointmentRPC {
    @RequestMapping(value = "/dummy_appointment/list_all_available", method = RequestMethod.GET)
    List<ConsultingRoom> findAvailableAppointments(@PathVariable("healthProviderInstituteName") String healthProviderInstituteName, @PathVariable("specialtyName") String specialtyName);

    @RequestMapping(value = "/dummy_appointment/create", method = RequestMethod.POST)
    CreateAppointmentResponse attemptToCreateAppointment(@RequestBody final CreateAppointment createAppointment);

    @RequestMapping(value = "/dummy_appointment/delete/{appointmentId}", method = RequestMethod.DELETE)
    Response attemptToDeleteAppointment(@PathVariable("appointmentId") final int appointmentId);

    @RequestMapping(value = "/dummy_appointment/list_user_appointments/{userDocument}", method = RequestMethod.GET)
    List<Appointment> findUserAppointments(@PathVariable("userDocument") final int userDocument);

    @RequestMapping(value = "/dummy_appointment/update", method = RequestMethod.PUT)
    Response attemptToUpdateAppointment(UpdateAppointment updateAppointment);

    @RequestMapping(value = "/dummy_appointment/find/{userDocument}/{appointmentId}")
    Appointment findUserAppointment(@PathVariable("userDocument") final int userDocument,
                                    @PathVariable("appointmentId") final int appointmentId);
}
