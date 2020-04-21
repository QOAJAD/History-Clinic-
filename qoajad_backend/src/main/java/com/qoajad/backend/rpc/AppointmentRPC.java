package com.qoajad.backend.rpc;

import com.qoajad.backend.model.appointment.Appointment;
import com.qoajad.backend.model.appointment.CreateAppointment;
import com.qoajad.backend.model.appointment.UpdateAppointment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AppointmentService", url = "http://localhost:9000")
@Qualifier("defaultAppointmentRPC")
public interface AppointmentRPC {

    @RequestMapping(value = "/appointment/find/{appointmentId}", method = RequestMethod.GET)
    Appointment findAppointment(@PathVariable("appointmentId") int appointmentId);

    @RequestMapping(value = "/appointment/create", method = RequestMethod.POST)
    boolean createAppointment(@RequestBody CreateAppointment appointment);

    @RequestMapping(value = "/appointment/update", method = RequestMethod.PUT)
    boolean updateAppointment(@RequestBody UpdateAppointment updateAppointment);
}
