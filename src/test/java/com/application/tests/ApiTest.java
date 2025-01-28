package com.application.tests;

import com.framework.commons.ApiCommons;

import org.testng.annotations.Test;

import com.api.pages.ApiPage;

import io.restassured.response.Response;

public class ApiTest extends ApiCommons{
	
	@Test(priority=1)
	public void createRepositoryForValidUser() {
		Response response = getResponse("POST", "/user/repos", ApiPage.createRepositoryRequestBody("SampleRestAssuredRepo", true));
		verifyStatusCode(response, 201);
		verifyStatusMessage(response, "Created");
		verifyResponseTime(response, 2);
		verifyResponseBody(response, "name", "SampleRestAssuredRepo");
		verifyResponseBody(response, "private", "true");
	}
	
	@Test(priority=2)
	public void updateRepositoryForValidUser() {
		Response response = getResponse("PATCH", "/repos/bharathtechacademy4/SampleRestAssuredRepo", ApiPage.updateRepositoryRequestBody(false));
		verifyStatusCode(response, 200);
		verifyStatusMessage(response, "OK");
		verifyResponseTime(response, 2);
		verifyResponseBody(response, "name", "SampleRestAssuredRepo");
		verifyResponseBody(response, "private", "false");
	}
	
	@Test(priority=3)
	public void getRepositoryForValidUser() {
		Response response = getResponse("GET", "/repos/bharathtechacademy4/SampleRestAssuredRepo","");
		verifyStatusCode(response, 200);
		verifyStatusMessage(response, "OK");
		verifyResponseTime(response, 2);
		verifyResponseBody(response, "name", "SampleRestAssuredRepo");
		verifyResponseBody(response, "private", "false");
	}
	
	@Test(priority=4)
	public void deleteRepositoryForValidUser() {
		Response response = getResponse("DELETE", "/repos/bharathtechacademy4/SampleRestAssuredRepo","");
		verifyStatusCode(response, 204);
		verifyStatusMessage(response, "No Content");
		verifyResponseTime(response, 2);
	}

}
