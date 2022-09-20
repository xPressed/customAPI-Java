package ru.xpressed.customapijava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.xpressed.customapijava.repository.ContactRepository;

@Controller
public class ListController {
    private ContactRepository contactRepository;
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping({"/index", "/"})
    public String showListPage(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "index";
    }

    @GetMapping("/index/{id}")
    public String deleteContact(@PathVariable int id, Model model) {
        if (contactRepository.findById(id).isPresent()) {
            contactRepository.deleteById(id);
        }
        model.addAttribute("contacts", contactRepository.findAll());
        return "index";
    }
}
