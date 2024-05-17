package org.iesvdm.appointment.repository;

import org.iesvdm.appointment.entity.*;
import org.iesvdm.appointment.repository.impl.AppointmentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentRepositoryImplTest {

    private Set<Appointment> appointments;

    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setup() {
        appointments = new HashSet<>();
        appointmentRepository = new AppointmentRepositoryImpl(appointments);
    }

    /**
     * Crea 2 citas (Appointment) una con id 1 y otra con id 2,
     * resto de valores inventados.
     * Agrégalas a las citas (appointments) con la que
     * construyes el objeto appointmentRepository bajo test.
     * Comprueba que cuando invocas appointmentRepository.getOne con uno
     * de los id's anteriores recuperas obtienes el objeto.
     * Pero si lo invocas con otro id diferente recuperas null
     */
    @Test
    void getOneTest() {
        //When
        Appointment appointment1 = new Appointment();
        appointment1.setId(1);
        Appointment appointment2 = new Appointment();
        appointment2.setId(2);

        //do
        appointments.add(appointment1);
        appointments.add(appointment2);

        //Then
        assertThat(appointmentRepository.getOne(1)).isEqualTo(appointment1);
        assertThat(appointmentRepository.getOne(2)).isEqualTo(appointment2);
        assertThat(appointmentRepository.getOne(3)).isNull();
    }

    /**
     * Crea 2 citas (Appointment) y guárdalas mediante
     * appointmentRepository.save.
     * Comprueba que la colección appointments
     * contiene sólo esas 2 citas.
     */
    @Test
    void saveTest() {
        //When
        Appointment appointment1 = new Appointment();
        appointment1.setId(1);
        Appointment appointment2 = new Appointment();
        appointment2.setId(2);

        //do
        appointments.add(appointment1);
        appointments.add(appointment2);

        //Then
        assertThat(appointments.size()).isEqualTo(2);
    }

    /**
     * Crea 2 citas (Appointment) una cancelada por un usuario y otra no,
     * (atención al estado de la cita, lee el código) y agrégalas mediante
     * appointmentRepository.save a la colección de appointments
     * Comprueba que mediante appointmentRepository.findCanceledByUser
     * obtienes la cita cancelada.
     */
    @Test
    void findCanceledByUserTest() {
        //When
        Appointment appointment1 = new Appointment();
        appointment1.setStatus(AppointmentStatus.CANCELED);
        Appointment appointment2 = new Appointment();
        appointment2.setStatus(AppointmentStatus.CONFIRMED);

        //do
        appointments.add(appointment1);
        appointments.add(appointment2);

        //Then
        assertThat(appointmentRepository.findCanceledByUser(1)).isEqualTo(appointment1);

    }

    /**
     * Crea 3 citas (Appointment), 2 para un mismo cliente (Customer)
     * con sólo una cita de ellas presentando fecha de inicio (start)
     * y fin (end) dentro del periodo de búsqueda (startPeriod,endPeriod).
     * Guárdalas mediante appointmentRepository.save.
     * Comprueba que appointmentRepository.findByCustomerIdWithStartInPeroid
     * encuentra la cita en cuestión.
     * Nota: utiliza LocalDateTime.of(...) para crear los LocalDateTime
     */
    @Test
    void findByCustomerIdWithStartInPeroidTest() {

        //When
        Appointment appointment1 = new Appointment();
        appointment1.setId(1);
        Appointment appointment2 = new Appointment();
        appointment2.setId(1);
        Appointment appointment3 = new Appointment();
        appointment3.setId(1);
        appointment3.setStart(LocalDateTime.of(2020, 1, 1, 0, 0));
        appointment3.setEnd(LocalDateTime.of(2020, 1, 2, 0, 0));

        //do
        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);

        //Then
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);

        /** Devuelve false porque no encuentra la cita, cuando yo espero que devuelva true porque esta ya en la colección */
        assertThat(appointmentRepository.findByCustomerIdWithStartInPeroid(1, LocalDateTime.of(2020, 1, 1, 0, 0), LocalDateTime.of(2020, 1, 2, 0, 0)).contains(appointment3)).isTrue();
    }


    /**
     * Crea 2 citas (Appointment) una planificada (SCHEDULED) con tiempo fin
     * anterior a la tiempo buscado por appointmentRepository.findScheduledWithEndBeforeDate
     * guardándolas mediante appointmentRepository.save para la prueba de findScheduledWithEndBeforeDate
     *
     */
    @Test
    void findScheduledWithEndBeforeDateTest() {

    }


    /**
     * Crea 3 citas (Appointment) planificadas (SCHEDULED)
     * , 2 para un mismo cliente, con una elegible para cambio (con fecha de inicio, start, adecuada)
     * y otra no.
     * La tercera ha de ser de otro cliente.
     * Guárdalas mediante appointmentRepository.save
     * Comprueba que getEligibleAppointmentsForExchange encuentra la correcta.
     */
    @Test
    void getEligibleAppointmentsForExchangeTest() {

    }


    /**
     * Igual que antes, pero ahora las 3 citas tienen que tener
     * clientes diferentes y 2 de ellas con fecha de inicio (start)
     * antes de la especificada en el método de búsqueda para
     * findExchangeRequestedWithStartBefore
     */
    @Test
    void findExchangeRequestedWithStartBeforeTest() {

    }
}
