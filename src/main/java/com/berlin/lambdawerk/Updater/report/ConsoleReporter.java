package com.berlin.lambdawerk.Updater.report;

import org.springframework.stereotype.Service;


/**
 * Created by Chaklader on 2/23/17.
 */

@Service
public class ConsoleReporter implements Reporter {

	public void report(final String str) {
		System.out.println(str);
	}
}
