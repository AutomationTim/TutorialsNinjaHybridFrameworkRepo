package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.utilities;

public class MyListners implements ITestListener {

	public static final String WebDriver = null;
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {

		// lets generate here our extent report
		extentReport = ExtentReporter.generateExtentReport();

	}

	@Override
	public void onTestStart(ITestResult result) {

		// Lets create tests class in our extentReport
		extentTest = extentReport.createTest(result.getName());

		// lets create an INFO type extentTest log
		extentTest.log(Status.INFO, result.getName() + " Started executing...");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// lets create a PASS type extentTest log
		extentTest.log(Status.PASS, result.getName() + " got successfully passed...");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		// before using a screenshot function here, we need to get the relevant driver
		// by typing the following format:
		// add to all test classes (login,register,search) to their WebDriver driver a
		// "public" so it can be read by this

		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

	String destionationScreenshotPath =	utilities.captureScreenshot(driver, result.getName());		
		
		// lets attach the screenshot to the test report itself
		extentTest.addScreenCaptureFromPath(destionationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " Failed.");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " Got skipped...");

	}

	@Override
	public void onFinish(ITestContext context) {

		// IMPORTANT: its essential to add a flush otherwise all the test and info will
		// not be created in the test report
		extentReport.flush();
		
		String pathOFExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOFExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
