package com.berlin.lambdawerk.Updater.service;

import com.berlin.lambdawerk.Updater.exception.ProcessingException;

import java.io.InputStream;

/**
 * Created by Chaklader on 2/23/17.
 */
public interface Updater {

    void parseAndUpdate(InputStream inputStream) throws ProcessingException;
}
