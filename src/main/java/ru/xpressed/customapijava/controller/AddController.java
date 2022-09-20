package ru.xpressed.customapijava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.xpressed.customapijava.entity.Contact;
import ru.xpressed.customapijava.repository.ContactRepository;

import javax.validation.Valid;

@Controller
public class AddController {
    private ContactRepository contactRepository;
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/add")
    public String showAddingForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "adding";
    }

    @PostMapping("/add")
    public String completeAdding(@Valid Contact contact, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contact", contact);
            return "adding";
        }

        contactRepository.save(contact);
        model.addAttribute("message", "New Contact Was Added");
        return "adding";
    }
}
