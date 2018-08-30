package com.merlin.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.functionalComponents.Lib;
import com.functionalComponents.Report;
import com.functionalComponents.TestSetUp;

public class HomePage extends TestSetUp {

	// constructor to initialize driver
	public HomePage(WebDriver driver) {
		TestSetUp.driver = driver;
	}

	Lib lib = new Lib(driver);

	// ******** locators - UI elements to be interacted with ********//
	private By LOGOUT_LNK = By.xpath("//a[@title='Logout']");

	public boolean verifyUserLoggedIn() throws Exception {
		return lib.checkObjectExist(LOGOUT_LNK, " LogOut link ");
	}

	public LogInPage logOutFromMerlinApplication() throws Exception {
		lib.waitForElementVisible(driver, LOGOUT_LNK);
		lib.objectClick(LOGOUT_LNK, " LogOut link ");
		return new LogInPage(driver);
	}
}