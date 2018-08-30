package com.merlin.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.functionalComponents.Lib;
import com.functionalComponents.TestSetUp;

public class LogInPage extends TestSetUp {

	// constructor to initialize driver
	public LogInPage(WebDriver driver) {
		TestSetUp.driver = driver;
	}

	Lib lib = new Lib(driver);

	// ******** locators - UI elements to be interacted with ********//

	private By USERNAME_TXTBOX = By.xpath("//input[@id='username']");
	private By PASSWORD_TXTBOX = By.xpath("//input[@id='password']");
	private By LOGIN_BTN = By.xpath("//input[@value='Login']");
	private By DOMAIN_DRPDWN = By.xpath("//select[@id='domain']");
	private By INVALID_CREDENTIALS_ERROR_TXT = By
			.xpath("//div[@id='credentialCheck']");
	private By SKIP_AND_CONTINUE_BTN = By.xpath("//input[@value='Skip & Continue']");

	// ************* methods - services offered by the webPage *************//

	public boolean verifyLogInPageIsDisplayed() throws Exception {
		lib.waitForElementVisible(driver, LOGIN_BTN);
		return lib.checkObjectExist(LOGIN_BTN, " LogIn Button ");
	}

	public HomePage logInToMerlinApplication(String username, String password,
			String Domain) throws Exception {
		lib.waitForElementVisible(driver, USERNAME_TXTBOX);
		lib.enterValue(USERNAME_TXTBOX, " Username Text Box ", username);
		lib.enterValue(PASSWORD_TXTBOX, " Password Text Box ", password);
		lib.selectValueFromDropdown(DOMAIN_DRPDWN, " Domain Dropdown ", Domain);
		lib.objectClick(LOGIN_BTN, " LogIn Button ");
		Thread.sleep(2000);
		if(lib.checkObjectExist(SKIP_AND_CONTINUE_BTN, " Skip and continue button")==true){
			lib.objectClick(SKIP_AND_CONTINUE_BTN, " Skip and continue button");
		}
		return new HomePage(driver);
	}

	public void verifyErrorMessageDisplayedOnLoggingWithInvalidCredentilas()
			throws Exception {
		lib.waitForElementVisible(driver, INVALID_CREDENTIALS_ERROR_TXT);
		lib.checkObjectExist(INVALID_CREDENTIALS_ERROR_TXT,
				" Error Message for invalid credentials ");

	}

	public String validateErrorMessageOnLoggingWithInvalidCredentilas()
			throws Exception {
		return lib.getText(INVALID_CREDENTIALS_ERROR_TXT,
				" Error Message for invalid credentials ");
	}

}