package com.framework.reports;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports {
	
	// This class will have common functions related to test results report using extent reports
	
	public static ExtentHtmlReporter html = null;
	public static ExtentReports extent = null; 
	public static ExtentTest logger = null;
	
	
	// method to setup report
	@BeforeSuite(alwaysRun=true)
	public void setupReport() {
		ExtentHtmlReporter html = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationReport.html");
		extent = new ExtentReports(); 
		extent.attachReporter(html);
	}
	
	// method to start printing the test results
	public void startReporting(String testCaseName) {
		logger = extent.createTest(testCaseName);		
	}
	
	// method to stop printing the test results
	public void stopReporting() {
		extent.flush();	
	}


}
