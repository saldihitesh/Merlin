package com.merlin.test;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.functionalComponents.*;
import com.merlin.pageObjects.*;

public class LogInValidationTests extends TestSetUp {

	// data to be used in test
	private final String USERNAME = getCommonTestData("Value", "Username");
	private final String PASSWORD = getCommonTestData("Value", "Password");
	private final String DOMAIN = getCommonTestData("Value", "Domain");

	// instantiating various page objects
	LogInPage logIn = new LogInPage(driver);
	HomePage homePage = new HomePage(driver);

	// *********************** TEST SCRIPTS *********************//

	@Test(description = "This test scenario validates that a user is able to log in successfully to "
			+ " merlin application ")
	public void testLogInValidation() throws Exception {
		if (getTestRunStatus("Enabled Value", "testLogInValidation")
				.equalsIgnoreCase("TRUE"))
		{
			Report.getMessage("Application launched successfully");
			Assert.assertTrue(logIn.verifyLogInPageIsDisplayed(),
					"LogIn page is not displayed ");
			logIn.logInToMerlinApplication(USERNAME, PASSWORD, DOMAIN);
			Assert.assertTrue(homePage.verifyUserLoggedIn(),
					"Unable to log in to application");
			Report.getMessage("User Logged in successfully");
		} 
		else {
			Report.getMessage("Test marked as not to be run");
			throw new SkipException(
					"Skipping this test as enabled value retrieved is false");
		}
	}

	
	@Test(description = "This test scenario validates that appropriate "
			+ " error message is displayed when user tries to log in with invalid credentials ")
	public void testLogInErrorMessageValidation() throws Exception {
		if (getTestRunStatus("Enabled Value", "testLogInErrorMessageValidation")
				.equalsIgnoreCase("TRUE")) 
		{
			Report.getMessage("Application launched successfully");
			Assert.assertTrue(logIn.verifyLogInPageIsDisplayed(),
					"LogIn page is not displayed ");
			logIn.logInToMerlinApplication(
					getTestData("Username", "testLogInErrorMessageValidation"),
					getTestData("Password", "testLogInErrorMessageValidation"),
					DOMAIN);
			logIn.verifyErrorMessageDisplayedOnLoggingWithInvalidCredentilas();
			Assert.assertTrue(
					logIn.validateErrorMessageOnLoggingWithInvalidCredentilas()
							.contains(
									getTestData("ErrorMsg",
											"testLogInErrorMessageValidation")),
					"Invalid credentials error message is not displayed");
			Report.getMessage("User not able to log in with Invalid credentilas");
			Report.getMessage("Error Message Validated successfully");
		} 
		else 
		{
			Report.getMessage("Test marked as not to be run");
			throw new SkipException(
					"Skipping this test as enabled value retrieved is false");
		}
	}
	
	@Test(description = "This test scenario validates that a user is able to log out successfully "
			+ " from merlin application ")
	public void testLogOutValidation() throws Exception {
		if (getTestRunStatus("Enabled Value", "testLogOutValidation")
				.equalsIgnoreCase("TRUE"))
		{
			Report.getMessage("Application launched successfully");
			logIn.verifyLogInPageIsDisplayed();
			logIn.logInToMerlinApplication(USERNAME, PASSWORD, DOMAIN);
			Assert.assertTrue(homePage.verifyUserLoggedIn(),
					"Unable to log in to application");
			Report.getMessage("User Logged in successfully");
			homePage.logOutFromMerlinApplication();
			Assert.assertTrue(logIn.verifyLogInPageIsDisplayed(),
					"LogIn page is not displayed on logging out from application");
			Report.getMessage("User logged out successfully");
			Report.getMessage("Log In Page is Displayed");					
		} 
		else {
			Report.getMessage("Test marked as not to be run");
			throw new SkipException(
					"Skipping this test as enabled value retrieved is false");
		}
	}

}
