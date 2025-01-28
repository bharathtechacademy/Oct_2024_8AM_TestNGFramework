package com.framework.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.application.steps.CookiesPageSteps;
import com.application.steps.HomePageSteps;
import com.application.steps.LoginPageSteps;
import com.application.steps.SignUpPageSteps;
import com.framework.utils.ReadExcel;

public class BaseTest extends BasePage{
	
	public CookiesPageSteps cookiesPage = null;
	public LoginPageSteps loginPage = null;
	public SignUpPageSteps signUpPage = null;
	public HomePageSteps homePage = null;
	
	@BeforeMethod(alwaysRun=true, dependsOnMethods="setupBrowser")
	public void initializePages() {
		WebDriver driver = new BasePage().getDriver(); //driver comes from base page
		cookiesPage = new CookiesPageSteps(driver);
		loginPage = new LoginPageSteps(driver);
		signUpPage = new SignUpPageSteps(driver);
		homePage = new HomePageSteps(driver);		
	}	
	
	@DataProvider(name="data")
	public String [][] testdata(Method method){
		String [][] data = ReadExcel.readData("TestData.xlsx", method.getName());
		return data;
	}

}
