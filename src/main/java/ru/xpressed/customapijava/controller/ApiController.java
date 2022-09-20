package ru.xpressed.customapijava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.xpressed.customapijava.entity.Contact;
import ru.xpressed.customapijava.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {
    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/api/{id}")
    public <T> T getContactById(@PathVariable int id) {
        if (contactRepository.findById(id).isPresent())
            return (T) contactRepository.findById(id);
        return (T) "ID not found!";
    }

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

    @PostMapping("/api")
    public Contact postContactById(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @DeleteMapping("/api/{id}")
    public String deleteContactById(@PathVariable int id) {
        if (contactRepository.findById(id).isPresent()) {
            contactRepository.deleteById(id);
            return "Successful!";
        }
        return "ID not found!";
    }
}
