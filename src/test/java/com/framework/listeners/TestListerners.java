package com.framework.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.framework.base.BasePage;
import com.framework.reports.Reports;

public class TestListerners extends Reports implements ITestListener {
	
	// This class will have common functions related to testNG listeners to monitor events during execution process

	public void onTestStart(ITestResult result) {
		String testcase = result.getMethod().getMethodName();
		startReporting(testcase);
	}

	public void onTestSuccess(ITestResult result) {
		String testcase = result.getMethod().getMethodName();
		logger.pass("Test Execution is Successful for the Test "+testcase);
		stopReporting();
	}

	public void onTestFailure(ITestResult result) {
		String testcase = result.getMethod().getMethodName();
		logger.fail("Test Execution is Failed for the Test "+testcase);
		logger.fail("Test Execution is Failed due to : "+testcase);
		try {
			logger.addScreenCaptureFromPath(takeWindowScreenshot(testcase,new BasePage().getDriver()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stopReporting();
	}
	
	public static String takeWindowScreenshot(String screenshotName, WebDriver driver) throws IOException {
		String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+".png";
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(filePath));
		return filePath;
	}

}
