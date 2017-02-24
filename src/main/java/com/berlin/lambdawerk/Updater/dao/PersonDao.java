package com.berlin.lambdawerk.Updater.dao;

import com.berlin.lambdawerk.Updater.model.Person;
import com.berlin.lambdawerk.Updater.model.PersonPK;

/**
 * Created by Chaklader on 2/23/17.
 */
public interface PersonDao {

    Person getById(PersonPK id);

    void persist(Person person);

    void update(Person person);
}
