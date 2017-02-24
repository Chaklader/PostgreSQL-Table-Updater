package com.berlin.lambdawerk.Updater.parser;

import com.berlin.lambdawerk.Updater.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Chaklader on 2/23/17.
 */
public class PersonHandlerTest
        implements ParsingHandler<Person> {

    private PersonHandler personHandler;

    @Before
    public void before() {
        personHandler = new PersonHandler(this);
    }

    @Test
    public void simpleTest() throws ParserConfigurationException, SAXException, IOException {
        InputStream testData = getTestData();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(testData, personHandler);
    }

    protected InputStream getTestData() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("test_data.xml");
        return inputStream;
    }

    @Override
    public void handle(final Person bean) {
        System.out.println(bean);
    }
}
