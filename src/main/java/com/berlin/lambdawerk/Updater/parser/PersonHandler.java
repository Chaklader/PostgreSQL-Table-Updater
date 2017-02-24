package com.berlin.lambdawerk.Updater.parser;

import com.berlin.lambdawerk.Updater.model.Person;
import com.berlin.lambdawerk.Updater.model.PersonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chaklader on 2/23/17.
 */
public class PersonHandler
    extends DefaultHandler {

    private static final String MEMBER = "member";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String DATE_OF_BIRTH = "date-of-birth";
    private static final String PHONE = "phone";
    private ParsingHandler<Person> parsingHandler;
    private PersonBuilder personBuilder;
    private String currentElement = "";
    private SimpleDateFormat birthDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PersonHandler(final ParsingHandler<Person> parsingHandler) {
        this.parsingHandler = parsingHandler;
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes)
        throws SAXException {
        if (qName.equalsIgnoreCase(MEMBER)) {
            personBuilder = new PersonBuilder();
        }
        currentElement = qName.toLowerCase();
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (qName.equalsIgnoreCase(MEMBER)) {
            try {
                Person person = personBuilder.build();
                parsingHandler.handle(person);
            } catch (Exception e) {
                System.err.println("The entry cannot be updated." + e.getMessage());
            }
        }
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (StringUtils.isNotBlank(value)) {
            if (FIRST_NAME.equals(currentElement)) {
                personBuilder.firstName(value);
            }
            else if (LAST_NAME.equals(currentElement)) {
                personBuilder.lastName(value);
            }
            else if (DATE_OF_BIRTH.equals(currentElement)) {
                try {
                    Date dateOfBirth = birthDateFormat.parse(value);
                    personBuilder.dateOfBirth(dateOfBirth);
                } catch (ParseException e) {
                    throw new SAXException(e);
                }
            }
            else if (PHONE.equals(currentElement)) {
                personBuilder.phone(value);
            }
        }
    }

    public void setFirthDateFormat(final String dateFormat) {
        birthDateFormat = new SimpleDateFormat(dateFormat);
    }
}
