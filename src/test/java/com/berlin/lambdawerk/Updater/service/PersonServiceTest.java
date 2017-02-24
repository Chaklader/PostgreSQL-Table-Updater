package com.berlin.lambdawerk.Updater.service;

import com.berlin.lambdawerk.Updater.model.Person;
import com.berlin.lambdawerk.Updater.model.PersonPK;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;


/**
 * Created by Chaklader on 2/23/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test_config.xml")
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@Test
	public void testOpenDBConnection() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1934, 11, 27, 0, 0, 0);
		Person person = new Person();
		PersonPK id = new PersonPK("NAVEAHA","PERDRIX", calendar.getTime());
		person.setId(id);
		person.setPhone("0501932760");
		personService.updatePerson(person);
	}
}
