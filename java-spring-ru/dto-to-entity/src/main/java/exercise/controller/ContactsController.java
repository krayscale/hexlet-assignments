package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create (ContactCreateDTO contactCreateDTO){

        var contact = toEntity(contactCreateDTO);
        contactRepository.save(contact);
        return toDto(contact);
    }


    private Contact toEntity(ContactCreateDTO contactCreateDTO){
        return Contact.builder()
                .firstName(contactCreateDTO.getFirstName())
                .lastName(contactCreateDTO.getLastName())
                .phone(contactCreateDTO.getPhone()).build();
    }

    private ContactDTO toDto(Contact contact)
    {
        return ContactDTO.builder()
                .id(contact.getId())
                .phone(contact.getPhone())
                .lastName(contact.getLastName())
                .firstName(contact.getFirstName())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }

}
