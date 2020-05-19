package com.qoaj.qoajbackend.unit.model.appointment;

import com.qoajad.backend.model.external.eps.appointment.AvailableAppointment;
import com.qoajad.backend.model.external.eps.appointment.ConsultingRoom;
import com.qoajad.backend.model.external.eps.appointment.CreateAppointment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentUnitTest {

    @Test(expected = NullPointerException.class)
    public void TestAvailableAppointmentConstructorFieldsAreInvalidAndThrowsException() {
        new AvailableAppointment(null, 1144099495, new Date(System.currentTimeMillis()));
    }

    @Test(expected = IllegalStateException.class)
    public void TestAvailableAppointmentConstructorFieldsAreInvalidAndThrowsException2() {
        new AvailableAppointment("Camilo Medina", 0, new Date(System.currentTimeMillis()));
    }

    @Test(expected = NullPointerException.class)
    public void TestAvailableAppointmentConstructorFieldsAreInvalidAndThrowsException3() {
        new AvailableAppointment("Andres Ricardo Mercedes", 16748021, null);
    }

    @Test
    public void testAvailableAppointmentConstructorAssignsLocalFieldsCorrectly() {
        final int document = 66480784;
        final String doctorName = "Carlos Ramiro";
        // YYYY-MM-DD
        final String dateFormat = "1997-12-03";
        final Date date = Date.valueOf(dateFormat);

        final AvailableAppointment availableAppointment = new AvailableAppointment(doctorName, document, date);
        Assert.assertEquals(availableAppointment.getDoctorName(), doctorName);
        Assert.assertEquals(availableAppointment.getDoctorDocument(), document);
        Assert.assertEquals(availableAppointment.getDate(), date);
    }

    @Test(expected = IllegalStateException.class)
    public void TestCreateAppointmentConstructorFieldsAreInvalidAndThrowsException() {
        new CreateAppointment(0, new Date(System.currentTimeMillis()), 1144099495, "Sura");
    }

    @Test(expected = NullPointerException.class)
    public void TestCreateAppointmentConstructorFieldsAreInvalidAndThrowsException2() {
        new CreateAppointment(16748055, null, 1144099495, "Sura");
    }

    @Test(expected = IllegalStateException.class)
    public void TestCreateAppointmentConstructorFieldsAreInvalidAndThrowsException3() {
        new CreateAppointment(16748055, new Date(System.currentTimeMillis()), 0, "Sura");
    }

    @Test(expected = NullPointerException.class)
    public void TestCreateAppointmentConstructorFieldsAreInvalidAndThrowsException4() {
        new CreateAppointment(16748055, new Date(System.currentTimeMillis()), 1144099495, null);
    }

    @Test
    public void testCreateAppointmentConstructorAssignsLocalFieldsCorrectly() {
        final int patientDocument = 16748999;
        final String scheduledBy = "Lorena Vargas";
        final int doctorDocument = 2121212;
        // YYYY-MM-DD
        final String dateFormat = "2018-01-10";
        final Date date = Date.valueOf(dateFormat);
        final String healthProviderInstitute = "Sura";

        final CreateAppointment createAppointment = new CreateAppointment(patientDocument, date, doctorDocument, healthProviderInstitute);
        Assert.assertEquals(createAppointment.getPatientDocument(), patientDocument);
        Assert.assertEquals(createAppointment.getDate(), date);
        Assert.assertEquals(createAppointment.getDoctorDocument(), doctorDocument);
        Assert.assertEquals(createAppointment.getHealthProviderInstituteName(), healthProviderInstitute);
    }

    @Test(expected = NullPointerException.class)
    public void TestConsultingRoomConstructorFieldsAreInvalidAndThrowsException() {
        new ConsultingRoom(null, Collections.EMPTY_LIST);
    }

    @Test(expected = NullPointerException.class)
    public void TestConsultingRoomConstructorFieldsAreInvalidAndThrowsException2() {
        new ConsultingRoom("consultorio 301", null);
    }

    @Test
    public void testConsultingRoomConstructorAssignsLocalFieldsCorrectly() {
        final String consultingRoomName = "Consultorio 3450";
        final AvailableAppointment availableAppointment1 = new AvailableAppointment("Camilo Ergueta", 1616161, new Date(System.currentTimeMillis()));

        final String dateFormat = "2008-05-20";
        final Date date = Date.valueOf(dateFormat);
        final AvailableAppointment availableAppointment2 = new AvailableAppointment("Daniel Bonilla", 121211, date);

        final List<AvailableAppointment> availableAppointments = new ArrayList<>();
        availableAppointments.add(availableAppointment1);
        availableAppointments.add(availableAppointment2);

        final ConsultingRoom consultingRoom = new ConsultingRoom(consultingRoomName, availableAppointments);

        Assert.assertEquals(consultingRoom.getName(), consultingRoomName);
        Assert.assertEquals(consultingRoom.getAvailableAppointments(), availableAppointments);
    }
}
