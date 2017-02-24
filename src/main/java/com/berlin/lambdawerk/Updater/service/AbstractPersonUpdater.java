package com.berlin.lambdawerk.Updater.service;

import com.berlin.lambdawerk.Updater.parser.ParsingHandler;
import com.berlin.lambdawerk.Updater.parser.PersonHandler;
import com.berlin.lambdawerk.Updater.report.Reporter;
import com.berlin.lambdawerk.Utils.Utils;
import com.berlin.lambdawerk.Updater.exception.ProcessingException;
import com.berlin.lambdawerk.Updater.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by Chaklader on 2/23/17.
 */
public abstract class AbstractPersonUpdater
    implements Updater, ParsingHandler<Person> {

    @Autowired
    protected Reporter reporter;

    @Autowired
    private PersonService personService;

    private long calls;
    private long updated;
    private long not_updated;
    private long created;
    private long errors;
    private long total;
    private long startTime;

    @Override
    public void parseAndUpdate(final InputStream inputStream) throws ProcessingException {
        beginMeasure();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            PersonHandler personHandler = new PersonHandler(this);
            saxParser.parse(inputStream, personHandler);
        } catch (ParserConfigurationException e) {
            throw new ProcessingException(e);
        } catch (SAXException e) {
            throw new ProcessingException(e);
        } catch (IOException e) {
            throw new ProcessingException(e);
        }
        endMeasure();
    }

    protected void doUpdatePerson(final Person person) {
        long updateStartTime = new Date().getTime();
        UpdateResult result;
        try {
            result = personService.updatePerson(person);
        } catch (Exception e) {
            result = UpdateResult.ERROR;
        }

        long duration =  new Date().getTime() - updateStartTime;
        Thread thread = Thread.currentThread();
        reporter.report(thread.getName() + " " + person + " - " + duration + "ms" + " result: " + result);

        synchronized (this) {
            
            calls += 1L;
            total += duration;

            switch (result) {
                        
                case UPDATED:
                    updated += 1L;
                    break;
                case CREATED:
                    created += 1L;
                    break;
                case NOT_UPDATED:
                    not_updated += 1L;
                    break;
                case ERROR:
                    errors += 1L;
                    break;
                }
        }
    }

    protected void beginMeasure() {
        calls = 0L;
        total = 0L;
        updated = 0L;
        not_updated = 0L;
        created = 0L;
        errors = 0L;
        startTime = new Date().getTime();
    }

    protected void endMeasure() {
        long execTime = new Date().getTime() - startTime;
        reporter.report("Average time: " + getAverage() + "ms");
        reporter.report("Execution time: " + Utils.getDurationStr(execTime));
        reporter.report("Updated: " + updated);
        reporter.report("Created: " + created);
        reporter.report("Not updated: " + not_updated);
        reporter.report("Errors: " + errors);
        reporter.report("Total number of persons: " + calls);
    }

    protected long getAverage() {
        if (calls == 0) {
            return 0;
        }
        return total / calls;
    }

    public void setReporter(final Reporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public abstract void handle(final Person bean);
}
