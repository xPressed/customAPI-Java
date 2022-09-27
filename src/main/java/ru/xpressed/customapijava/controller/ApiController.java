package ru.xpressed.customapijava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.xpressed.customapijava.entity.Contact;
import ru.xpressed.customapijava.exception.ContactNotFoundException;
import ru.xpressed.customapijava.repository.ContactRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {
    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Operation(summary = "GET Contact by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact found", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Contact NOT found", content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/api/{id}")
    public Optional<Contact> getContactById(@PathVariable int id) {
            return Optional.ofNullable(contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact NOT found")));
    }

    @Operation(summary = "GET all Contacts")
    @GetMapping("/api")
    public List<Contact> getContacts(@RequestParam("contactName") Optional<String> contactName,
                                     @RequestParam("contactNumber") Optional<String> contactNumber) {
        if (contactName.isPresent() & contactNumber.isPresent())
            return contactRepository.findByContactNameAndContactNumber(contactName.get(), contactNumber.get());
        if (contactName.isPresent())
            return contactRepository.findByContactName(contactName.get());
        if (contactNumber.isPresent())
            return contactRepository.findByContactNumber(contactNumber.get());
        return contactRepository.findAll();
    }

    @Operation(summary = "POST Contact")
    @PostMapping("/api")
    public Contact postContactById(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @Operation(summary = "DELETE Contact by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact found", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Contact NOT found", content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/api/{id}")
    public String deleteContactById(@PathVariable int id) {
        if (contactRepository.findById(id).isPresent()) {
            contactRepository.deleteById(id);
            return "Successful!";
        }
        throw new ContactNotFoundException("Contact NOT found");
    }

    @Operation(summary = "DELETE all Contacts")
    @DeleteMapping("/api")
    @Transactional
    public String deleteContacts(@RequestParam("contactName") Optional<String> contactName,
                                 @RequestParam("contactNumber") Optional<String> contactNumber) {
        if (contactName.isPresent() & contactNumber.isPresent()) {
            contactRepository.deleteByContactNameAndContactNumber(contactName.get(), contactNumber.get());
            return "Successful!";
        }
        if (contactName.isPresent()) {
            contactRepository.deleteByContactName(contactName.get());
            return "Successful!";
        }
        if (contactNumber.isPresent()) {
            contactRepository.deleteByContactNumber(contactNumber.get());
            return "Successful!";
        }
        contactRepository.deleteAll();
        return "All records deleted!";
    }
}
