package com.berlin.lambdawerk.Updater.dao;

import com.berlin.lambdawerk.Updater.model.Person;
import com.berlin.lambdawerk.Updater.model.PersonPK;
import org.springframework.stereotype.Repository;

/**
 * Created by Chaklader on 2/23/17.
 */
@Repository
public class PersonDaoImpl
    extends AbstractDao<PersonPK, Person> implements PersonDao {

    @Override
    public Person getById(final PersonPK id) {
        return getByKey(id);
    }
}
