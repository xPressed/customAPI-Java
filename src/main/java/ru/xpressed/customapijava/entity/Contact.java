package ru.xpressed.customapijava.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "contact")
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can not be empty!")
    private String contactName;

    @NotEmpty(message = "Number can not be empty!")
    private String contactNumber;
}
