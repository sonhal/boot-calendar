package no.nav.bootcalendar.contact;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CONTACT")
public class Contact {

    private static final String ID_COLUMN = "CONTACT_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_" + ID_COLUMN)
    @SequenceGenerator(name = "SEQ_" + ID_COLUMN, sequenceName = "SEQ_" + ID_COLUMN, allocationSize = 1)
    @Column(name = ID_COLUMN, nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @NotNull
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "SECRET")
    private String secret;
}
