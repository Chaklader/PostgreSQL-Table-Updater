package com.berlin.lambdawerk.Updater.service;

import com.berlin.lambdawerk.Updater.dao.PersonDao;
import com.berlin.lambdawerk.Updater.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by Chaklader on 2/23/17.
 */
@Service
public class PersonServiceImpl
        implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public UpdateResult updatePerson(final Person person) {
        UpdateResult result = UpdateResult.NOT_UPDATED;
        Person found = personDao.getById(person.getId());
        if (isNeedToUpdate(person, found)) {
            if (found == null) {
                personDao.persist(person);
                result = UpdateResult.CREATED;
            } else {
                copyFieldsToUpdate(person, found);
                personDao.update(found);
                result = UpdateResult.UPDATED;
            }
        }
        return result;
    }

    protected void copyFieldsToUpdate(final Person src, final Person dest) {
        dest.setPhone(src.getPhone());
    }

    protected boolean isNeedToUpdate(final Person person, final Person found) {
        boolean result = found == null || !Objects.equals(found.getPhone(), person.getPhone());
        return result;
    }
}
