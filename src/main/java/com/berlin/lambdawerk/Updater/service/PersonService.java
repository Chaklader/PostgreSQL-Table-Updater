package com.berlin.lambdawerk.Updater.service;

import com.berlin.lambdawerk.Updater.model.Person;

/**
 * Created by Chaklader on 2/23/17.
 */
public interface PersonService {

    UpdateResult updatePerson(Person person);
}
