package no.nav.bootcalendar.appointment;

import no.nav.bootcalendar.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByTitle(String title);
}
