package com.berlin.lambdawerk.Updater.service;

import com.berlin.lambdawerk.Updater.exception.ProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

/**
 * Created by Chaklader on 2/23/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test_config.xml")
public class PersonUpdaterTest {

    @Autowired
    private Updater updater;

    @Test
    public void sipleTest() throws ProcessingException {
        InputStream testData = getTestData();
        updater.parseAndUpdate(testData);
    }

    protected InputStream getTestData() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("update_test_data.xml");
        return inputStream;
    }
}
