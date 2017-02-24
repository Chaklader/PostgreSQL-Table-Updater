package com.berlin.lambdawerk.Updater;

import com.berlin.lambdawerk.Updater.service.Updater;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Chaklader on 2/23/17.
 */
public class Main {

	static ApplicationContext ctx;
	
	public static void main(final String[] args) throws IOException {
		
		ctx = new FileSystemXmlApplicationContext("config.xml");
		Updater updater = (Updater) ctx.getBean("personUpdater");

		if (args.length == 0) {
			System.out.println("Input filename undefined");
			return;
		}
		String fileName = args[0];
		
		File inputFile = new File(fileName);
		if (inputFile.exists()) {
			InputStream inputStream = null;
			BufferedReader reader = null;
			try{
				inputStream = new FileInputStream(inputFile);
				updater.parseAndUpdate(inputStream);
			} catch (FileNotFoundException e) {
				System.out.println(fileName + " file not found");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());	
			}
			finally {
				if (inputStream != null){
					inputStream.close();
				}
				if (reader != null){
					reader.close();
				}
			} 
		} else {
			System.out.println(fileName + " file not found");
		}
	}
}
