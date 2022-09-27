package ru.xpressed.customapijava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xpressed.customapijava.entity.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByContactName(String contactName);
    List<Contact> findByContactNumber(String contactNumber);
    List<Contact> findByContactNameAndContactNumber(String contactName, String contactNumber);

    void deleteByContactName(String contactName);
    void deleteByContactNumber(String contactNumber);
    void deleteByContactNameAndContactNumber(String contactName, String contactNumber);
}
