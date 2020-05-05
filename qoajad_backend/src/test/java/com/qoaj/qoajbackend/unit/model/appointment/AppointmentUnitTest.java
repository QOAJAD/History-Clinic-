package com.qoaj.qoajbackend.unit.model.appointment;

import com.qoajad.backend.model.appointment.Appointment;
import com.qoajad.backend.model.appointment.CreateAppointment;
import com.qoajad.backend.model.appointment.UpdateAppointment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentUnitTest {

    @Test(expected = IllegalStateException.class)
    public void TestAppointmentConstructorFieldsAreInvalidAndThrowsException() {
        new Appointment(0, "Mario Medina", new Date(System.currentTimeMillis()));
    }

    @Test(expected = NullPointerException.class)
    public void TestAppointmentConstructorFieldsAreInvalidAndThrowsException2() {
        new Appointment(1, null, new Date(System.currentTimeMillis()));
    }

    @Test(expected = NullPointerException.class)
    public void TestAppointmentConstructorFieldsAreInvalidAndThrowsException3() {
        new Appointment(1, "Ramiro Ergueta", null);
    }

    @Test
    public void testAppointmentConstructorAssignsLocalFieldsCorrectly() {
        final int appointmentId = 15;
        final String scheduledBy = "Carlos Ramiro";
        // YYYY-MM-DD
        final String dateFormat = "1997-12-03";
        final Date date = Date.valueOf(dateFormat);

        final Appointment appointment = new Appointment(appointmentId, scheduledBy, date);
        Assert.assertEquals(appointment.getId(), appointmentId);
        Assert.assertEquals(appointment.getScheduledBy(), scheduledBy);
        Assert.assertEquals(appointment.getDate(), date);
    }

    @Test
    public void testCreateAppointmentConstructorAssignsLocalFieldsCorrectly() {
        final String scheduledBy = "Lorena Vargas";
        // YYYY-MM-DD
        final String dateFormat = "2018-01-10";
        final Date date = Date.valueOf(dateFormat);

        final CreateAppointment createAppointment = new CreateAppointment(scheduledBy, date);
        Assert.assertEquals(createAppointment.getScheduledBy(), scheduledBy);
        Assert.assertEquals(createAppointment.getDate(), date);
    }

    @Test
    public void testUpdateAppointmentConstructorAssignsLocalFieldsCorrectly() {
        final int appointmentId = 21;
        final String scheduledBy = "Pedro Andrade";
        // YYYY-MM-DD
        final String dateFormat = "2020-05-10";
        final Date date = Date.valueOf(dateFormat);

        final UpdateAppointment updateAppointment = new UpdateAppointment(appointmentId, scheduledBy, date);
        Assert.assertEquals(updateAppointment.getId(), appointmentId);
        Assert.assertEquals(updateAppointment.getScheduledBy(), scheduledBy);
        Assert.assertEquals(updateAppointment.getDate(), date);
    }
}
