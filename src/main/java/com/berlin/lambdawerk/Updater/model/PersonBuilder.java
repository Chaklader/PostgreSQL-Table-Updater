package com.berlin.lambdawerk.Updater.model;

import java.util.Date;

/**
 * Created by Chaklader on 2/23/17.
 */
public class PersonBuilder {
    
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;

    public PersonBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder dateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public PersonBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public Person build() {
        if (firstName == null) {
            throw new IllegalStateException("First name is null");
        }
        if (lastName == null) {
            throw new IllegalStateException("Last name is null");
        }
        if (dateOfBirth == null) {
            throw new IllegalStateException("Date of birth is null");
        }
        Person person = new Person();
        PersonPK id = new PersonPK();
        id.setFirstName(firstName);
        id.setLastName(lastName);
        id.setDateOfBirth(dateOfBirth);
        person.setId(id);
        person.setPhone(phone);
        return person;
    }
}
