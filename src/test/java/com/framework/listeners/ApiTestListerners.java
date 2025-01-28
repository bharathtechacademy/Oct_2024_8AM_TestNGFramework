package com.framework.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.framework.reports.Reports;

public class ApiTestListerners extends Reports implements ITestListener {
	
	// This class will have common functions related to testNG listeners to monitor events during API Test execution process

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
		stopReporting();
	}
}
