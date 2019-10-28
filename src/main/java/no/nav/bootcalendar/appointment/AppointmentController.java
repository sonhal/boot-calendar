package no.nav.bootcalendar.appointment;

import no.nav.bootcalendar.common.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentRepository repository;
    private final AppointmentValidator validator;

    @GetMapping
    public List<Appointment> getAllAppointments(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @PostMapping
    public ResponseEntity<?> createNewAppointment(@RequestBody Appointment appointment) {
        try {
            validator.validate(appointment);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(repository.save(appointment));
    }

    @DeleteMapping("/{id}")
    public Long deleteAppointment(@PathVariable Long id){
        repository.deleteById((id));
        return id;
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Appointment appointment){
        Appointment savedAppointment = repository.findById(appointment.getId()).orElseThrow(IllegalArgumentException::new);

        if(appointment.getPlace() == null) appointment.setPlace(savedAppointment.getPlace());
        if(appointment.getStatus() == null) appointment.setStatus(savedAppointment.getStatus());
        if(appointment.getTime() == null) appointment.setTime(savedAppointment.getTime());
        if(appointment.getTitle() == null) appointment.setTitle(savedAppointment.getTitle());
        return repository.save(appointment);
    }

    @GetMapping("/test")
    public Appointment createTestData(){
        return repository.save(
                new Appointment(1L,
                "Test Appointment",
                LocalDateTime.now(),
                "Test Place",
                AppointmentStatus.SUBMITTED
                ));
    }

}
