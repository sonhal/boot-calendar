package no.nav.bootcalendar.appointment;

import no.nav.bootcalendar.common.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentValidator {

    public void validate(Appointment appointment) throws ValidationException {
        List<String> errors = new ArrayList<>();
        if(appointment.getTime() == null || appointment.getTime().isBefore(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS))) errors.add("time("+ appointment.getTime() +") is invalid" );
        if(appointment.getPlace() == null || appointment.getPlace().isEmpty()) errors.add("place("+ appointment.getPlace() +") is invalid" );
        if(appointment.getTitle() == null || appointment.getTitle().isEmpty()) errors.add("title("+ appointment.getPlace() +") is invalid" );

        if(!errors.isEmpty()) throw new ValidationException(errors);
    }
}
