package com.application.tests;

import org.testng.annotations.Test;

import com.framework.base.BaseTest;

public class ApplicationTest extends BaseTest {	
	
	@Test(priority=1)
	public void verifyCookiesPopUpIsDisplayed() {
		loginPage.launchApplication();
		cookiesPage.verifyWhetherCookiesPopUpIsDisplayed();
	}
	
	@Test(priority=2,dataProvider="data")
	public void verifyCookiesPopUpContent(String content) {
		loginPage.launchApplication();
		cookiesPage.verifyWhetherCookiesPopUpIsDisplayed();
		cookiesPage.verifyCookiesPopUpContent(content);
		cookiesPage.verifyCookiesPopUpLogos();
		cookiesPage.verifyCookiesSelectionButtons();
	}
	
	@Test(priority=3)
	public void verifyCookiesPopUpSwitchButtons() {
		loginPage.launchApplication();
		cookiesPage.verifyWhetherCookiesPopUpIsDisplayed();
		cookiesPage.verifyCookiesPopUpNecessarySwitchButtonIsDisabled();
		cookiesPage.verifyCookiesPopUpSwitchButtons();
	}
	
	@Test(priority=4)
	public void verifyCookiesPopUpViewDetailsLink() {
		loginPage.launchApplication();
		cookiesPage.verifyWhetherCookiesPopUpIsDisplayed();
		cookiesPage.clickOnShowDetailsLink();
		cookiesPage.verifyCookiesPopUpShowDetails();
	}
	
	@Test(priority=5)
	public void verifyCookiesWhenUserSelectsAcceptAll() {
		loginPage.launchApplication();
		cookiesPage.verifyWhetherCookiesPopUpIsDisplayed();
		cookiesPage.selectCookies("AllowAll");
		cookiesPage.verifyCookiesPopUpGettingDisappeared();
	}
	
	@Test(priority=6,dataProvider="data")
	public void verifyApplicationSignUp(String user, String pass) {
		loginPage.launchApplication();
		cookiesPage.verifyWhetherCookiesPopUpIsDisplayed();
		cookiesPage.selectCookies("AllowAll");
		loginPage.clickOnSignUpLink();
		signUpPage.verifySignUpPageIsLaunched();
		signUpPage.enterUserDetails(user, pass);
		signUpPage.clickOnContinueButton();
		signUpPage.enterCompanyDetails();
		signUpPage.clickOnSignUpButton();
		homePage.verifySignUpIsSuccessful();
	}
	
	@Test(priority=7,dataProvider="data")
	public void verifyApplicationLogin(String user, String pass) {
		loginPage.launchApplication();
		loginPage.enterCredentials(user, pass);
		loginPage.clickOnLoginButton();
		homePage.verifyLoginIsSuccessful();
	}
}
