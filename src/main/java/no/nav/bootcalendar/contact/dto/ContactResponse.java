package no.nav.bootcalendar.contact.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactResponse {

    private final long id;
    private final String firstName;
    private final String lastName;

}
