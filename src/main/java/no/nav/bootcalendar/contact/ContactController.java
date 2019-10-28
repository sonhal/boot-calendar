package no.nav.bootcalendar.contact;

import lombok.AllArgsConstructor;
import lombok.val;
import no.nav.bootcalendar.contact.dto.ContactResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor
public class ContactController {

    private final ContactRepository repository;

    @GetMapping
    public ResponseEntity<?> getAllContacts(){
        return new ResponseEntity<>(mapList(repository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id){
        val contact = repository.findById(id);
        if(contact.isPresent()){
            return new ResponseEntity<>(map(contact.get()), HttpStatus.OK);
        }
        return (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    @PostMapping
    public Contact createNewContact(@RequestBody Contact contact){
        return repository.save(contact);
    }

    @DeleteMapping("/{id}")
    public Long deleteContact(@PathVariable Long id){
        repository.deleteById((id));
        return id;
    }

    @GetMapping("/test")
    public Contact createTestData(){
        return repository.save(
                new Contact(
                        1L,
                "TestContact",
                "TestLastname",
                        "SECRET INFO")
        );
    }

    private ContactResponse map(Contact dao){
        return ContactResponse.builder()
                .id(dao.getId())
                .firstName(dao.getLastName())
                .lastName(dao.getLastName())
                .build();
    }

    private List<ContactResponse> mapList(List<Contact> contacts){
        return contacts.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

}
