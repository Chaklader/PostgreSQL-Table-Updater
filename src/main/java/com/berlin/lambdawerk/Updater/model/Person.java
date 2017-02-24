package com.berlin.lambdawerk.Updater.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.SimpleDateFormat;

/**
 * Created by Chaklader on 2/23/17.
 */
@Entity
@Table(name = "person")
public class Person {

    @EmbeddedId
    private PersonPK id;

    @Column(name = "phone")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public PersonPK getId() {
        return id;
    }

    public void setId(final PersonPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id.getFirstName()).append(' ');
        sb.append(id.getLastName()).append(' ');
        if (id.getDateOfBirth() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            sb.append(dateFormat.format(id.getDateOfBirth())).append(' ');
        }
        sb.append(phone);
        return sb.toString();
    }
}
