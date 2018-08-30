package com.functionalComponents;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.functionalComponents.TestSetUp;

public class Lib extends TestSetUp {

	// constructor to initialize driver
	public Lib(WebDriver driver) {
		TestSetUp.driver = driver;
	}
	
	/*
	 * This method navigates to a specific URL It accepts two parameters, the
	 * URL to be navigated to and a specific reference name for the URL to be
	 * printed in the report for user reference
	 */
	
	public void navigateToURL(String url, String referenceName)
			throws Exception {

		driver.navigate().to(url);
		Assert.assertEquals(driver.getCurrentUrl(), url,
				"user not navigated to" + " " + referenceName + ": " + url);
	}

	/*
	 * This method handles the alert pop ups
	 */
	public void handleAlert() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method waits for presence of element on page i.e DOM
	 */

	public void waitForElementPresent(WebDriver driver, By by) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/*
	 * This method waits for visibility of element on page i.e it appears on UI
	 */
	public void waitForElementVisible(WebDriver driver, By by) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	}

	public int getElementCount(By by, String objLogicalName) throws Exception {
		int size = 0;
		size = driver.findElements(by).size();
		return size;
	}

	public void objectClick(By by, String objLogicalName) {
		WebElement webElement = driver.findElement(by);
		webElement.click();
		Report.getMessage(objLogicalName + " is clicked ");
	}

	public void enterValue(By by, String objlogicalname, String objectValue)
			throws Exception {
		WebElement webElement = driver.findElement(by);
		webElement.clear();
		webElement.sendKeys(objectValue);
		Report.getMessage(objectValue + " entered in " + objlogicalname);
	}

	public void selectValueFromDropdown(By by, String objlogicalname,
			String objectValue) throws Exception {
		Select dropDownName = new Select(driver.findElement(by));
		dropDownName.selectByVisibleText(objectValue);
		Report.getMessage(objectValue + " selected from " + objlogicalname);
	}

	public String getText(By by, String objlogicalname) throws Exception {
		String txt = driver.findElement(by).getText();
		return txt;
	}

	public boolean checkObjectExist(By by, String objectlogicalname)
			throws Exception {
		boolean X = false;
		try {
			X = driver.findElement(by).isDisplayed();
		} catch (Exception e) {
		}
		return X;
	}

	public void mouseHover(By by, String objectlogicalname) throws Exception {
		Actions actions = new Actions(driver);
		WebElement hoverElement = driver.findElement(by);
		actions.moveToElement(hoverElement).build().perform();
		Thread.sleep(20000);
	}

	public void navigateBack() throws Exception {
		driver.navigate().back();
	}

	public String getAttributeValueText(By by, String attributeName,
			String objectlogicalname) {
		String text = driver.findElement(by).getAttribute(attributeName);
		return text;
	}

	public String getSelectedOptionValueFromDropdown(By by, String string) {
		Select dropDownName = new Select(driver.findElement(by));
		String text = dropDownName.getFirstSelectedOption().getText();
		return text;
	}

	public boolean verifyIsEnabled(By by, String objectlogicalname) {
		boolean X = driver.findElement(by).isEnabled();
		return X;
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public void dragAndDrop(By sourceElement, By targetElement) throws Exception{
		Thread.sleep(2000);
		WebElement sourceEl = driver.findElement(sourceElement);
		WebElement targetEl = driver.findElement(targetElement);
		Actions actions = new Actions(driver);
		actions.dragAndDrop(sourceEl, targetEl).build().perform();
		
	}
	
	public void clickUsingJavaScript(By by, String objLogicalName) throws Exception{
		WebElement element = driver.findElement(by);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Report.getMessage(objLogicalName + " is clicked ");
		
	}
	
	/*public void switchWindow() {
		String currWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		set.remove(currWindow);
		for (String newWindow : set) {
			driver.switchTo().window(newWindow);
		}
	}

	public void switchBackToParentWindow() {
		String currWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		set.remove(currWindow);
		for (String newWindow : set) {
			driver.switchTo().window(newWindow);
		}
		driver.switchTo().window(currWindow);
	}
*/

}
