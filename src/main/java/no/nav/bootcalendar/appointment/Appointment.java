package no.nav.bootcalendar.appointment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.nav.bootcalendar.contact.Contact;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "APPOINTMENT")
public class Appointment {

    private static final String ID_COLUMN = "APPOINTMENT_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_" + ID_COLUMN)
    @SequenceGenerator(name = "SEQ_" + ID_COLUMN, sequenceName = "SEQ_" + ID_COLUMN, allocationSize = 1)
    @Column(name = ID_COLUMN, nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotNull
    @Column(name = "TIME", nullable = false)
    private LocalDateTime time;

    @NotNull
    @Column(name = "PLACE", nullable = false)
    private String place;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private AppointmentStatus status;

}
