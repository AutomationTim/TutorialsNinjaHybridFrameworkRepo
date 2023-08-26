package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {

		// First lets create an extent report object (need to add a dependancy of
		// extentreport)
		ExtentReports extentReport = new ExtentReports();

		// Lets add a File path to the sparkReport by adding our project path
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");

		// lets choose a reporter type of: SparkReporter
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		// Configuring the extent report

		// set the theme of the report
		sparkReporter.config().setTheme(Theme.STANDARD);

		// set the name of the report
		sparkReporter.config().setReportName("My Test Automation Results");

		// Set the title of the web page
		sparkReporter.config().setDocumentTitle("Automation Report");

		// set the time stamp according to our format: "dd/MM/yyyy hh:mm:ss"
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		// Now , lets apply our sparkReporter to our extentReport
		extentReport.attachReporter(sparkReporter);

		// Lets create a new properties object and add it to our Config.properties file
		// in order to extract the generic URL and put it to our extentReport
		Properties configProp = new Properties();
		File configPropFile = new File(
				System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");

		// Add a try and catch because it asks to...
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		
		
		
		
		
		
		
		
		// set the generic url name from the config properties file.
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));

		// also , set the browser Name from the config properties file
		extentReport.setSystemInfo("Browser Name",configProp.getProperty("browserName"));

		// Provide the Email
		extentReport.setSystemInfo("Email",configProp.getProperty("ValidCredentialsEmail"));

		// Provide the Password
		extentReport.setSystemInfo("Password",configProp.getProperty("ValidCredentialsPw"));

		// Now, in order for us to add an OS version , java version , username who run
		// the test, we need to run this line: System.getProperties().list(System.out);
		// that way we will find out all the relevant info and then add.

		// add the OS
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));

		// add the username who runs the test
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));

		// add the java version
		extentReport.setSystemInfo("Java version", System.getProperty("java.version"));

		// return the extentReport with the return type of ExtentReports
		return extentReport;

		// Now we will go to MyListers class and generate this report in there.

	}
}
