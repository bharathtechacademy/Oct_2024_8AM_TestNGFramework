package com.framework.commons;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.jmeter.engine.StandardJMeterEngine;
//import org.apache.jmeter.report.config.ConfigurationException;
//import org.apache.jmeter.report.dashboard.GenerationException;
//import org.apache.jmeter.report.dashboard.ReportGenerator;
//import org.apache.jmeter.reporters.ResultCollector;
//import org.apache.jmeter.save.SaveService;
//import org.apache.jmeter.util.JMeterUtils;
//import org.apache.jorphan.collections.HashTree;
import org.testng.Assert;

import com.framework.reports.Reports;
import com.framework.utils.ReadProp;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiCommons extends Reports{
	
	// This class will have common functions related to API
	
	public static Properties prop = ReadProp.readData("Config.properties");
	
	// method to print messages in report
		public static void log(String status, String message) {
			if(status.equalsIgnoreCase("pass"))
				Reports.logger.pass(message);
			else if(status.equalsIgnoreCase("fail"))
				Reports.logger.fail(message);
			else if(status.equalsIgnoreCase("info"))
				Reports.logger.info(message);
			else if(status.equalsIgnoreCase("warning"))
				Reports.logger.warning(message);
		}

	// Method to get the response
	public static Response getResponse(String requestType, String endPoint, String requestBody) {
		Response response = null;
		RestAssured.baseURI = prop.getProperty("baseurl");
		String token = "Bearer " + prop.getProperty("token");

		switch (requestType) {

		case "GET":
			response = given().headers("Authorization", token).when().get(endPoint);
			break;
		case "DELETE":
			response = given().headers("Authorization", token).when().delete(endPoint);
			break;
		case "POST":
			response = given().headers("Authorization", token).body(requestBody).when().post(endPoint);
			break;
		case "PATCH":
			response = given().headers("Authorization", token).body(requestBody).when().patch(endPoint);
			break;
		case "PUT":
			response = given().headers("Authorization", token).body(requestBody).when().put(endPoint);
			break;
		default:
			Assert.fail("Invalid Request Type");
		}
		log("pass",response.getBody().asPrettyString());
		return response;
	}

	// Method to verify status code
	public static void verifyStatusCode(Response response, int expStatusCode) {
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, expStatusCode);
		log("pass","Status Code is Matching and Actual Status Code is :"+actualStatusCode);
	}

	// Method to verify status message
	public static void verifyStatusMessage(Response response, String expStatusMessage) {
		String actualStatus = response.getStatusLine();
		Assert.assertTrue(actualStatus.contains(expStatusMessage));
		log("pass","Status Message is Matching and Actual Status Message is :"+actualStatus);
	}

	// Method to verify Response time
	public static void verifyResponseTime(Response response, long expResponseTime) {
		long actualResponseTime = response.getTimeIn(TimeUnit.SECONDS);
		Assert.assertTrue(actualResponseTime <= expResponseTime);
		log("pass","Response time is as expected and actual response time is : "+actualResponseTime);
	}

	// Method to verify response body
	public static void verifyResponseBody(Response response, String key, String expValue) {
		String actualValue = response.getBody().jsonPath().getString(key);
		Assert.assertEquals(actualValue, expValue);
		log("pass","Response body is as expected and actual response body value is : "+actualValue+ " for the key : "+key);
	}

	// Method to verify key
	public static void verifyResponseKey(Response response, String key) {
		String status = response.getBody().jsonPath().get(key);
		Assert.assertNotNull(status);
	}
	
	// Method to verify response headers
	public void verifyResponseHeaders(Response response, String headerKey, String expHeaderValue) {
		String actualValue = response.getHeader(headerKey);
		Assert.assertEquals(actualValue, expHeaderValue);
	}

	// Method to run JMeter file
//	public static void runJmeterTest(String jmxFile) throws IOException, ConfigurationException, GenerationException {
//
//		String jmeterHome = "src/test/resources/jmeter";
//		String resultsFilePath = Paths.get(jmeterHome, "results", "TestResults.csv").toString();
//		String testPlanFilepath = Paths.get(jmeterHome, jmxFile).toString();
//
//		// Set JMeter home directory
//		JMeterUtils.setJMeterHome(jmeterHome);
//
//		// Load JMeter properties
//		//String propertiesFilePath = Paths.get(jmeterHome,"bin","jmeter.properties").toString();
//		String propertiesFilePath = System.getProperty("user.dir")
//				+ "\\src\\test\\resources\\jmeter\\bin\\jmeter.properties";
//		JMeterUtils.loadJMeterProperties(propertiesFilePath);
//
//		// Add JMeter test plan to run
//		File testPlanFile = new File(testPlanFilepath);
//		HashTree testPlanTree = SaveService.loadTree(testPlanFile);
//
//		// Configure Results collector to save the results and prepare the report
//		ResultCollector resultCollector = new ResultCollector();
//		resultCollector.setFilename(resultsFilePath);
//
//		// Add test results and generate report
//		StandardJMeterEngine jmeter = new StandardJMeterEngine();
//		testPlanTree.add(testPlanTree.getArray()[0], resultCollector);
//		jmeter.configure(testPlanTree);
//		jmeter.run();
//
//		// Generate Html Report
//		ReportGenerator reportGenerator = new ReportGenerator(resultsFilePath, null);
//		reportGenerator.generate();
//
//	}

}
