package com.functionalComponents;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.jetty7.util.log.Log;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.functionalComponents.TestDataDriver;

public class TestSetUp {

	// instantiating driver instance
	public static WebDriver driver;

	// declaring variable to get absolute path of data driver
	public String root = System.getProperty("user.dir");

	// instantiating objects of TestDataDriver to fetch testData
	TestDataDriver testEnableDisable = new TestDataDriver();
	TestDataDriver testData = new TestDataDriver();
	TestDataDriver testDataCommon = new TestDataDriver();

	// application URL required to launch the application
	String APP_URL = getCommonTestData("Value", "Application URL");
	String BROWSER_NAME = getCommonTestData("Value", "Browser");
	String BROWSER_PATH = getCommonTestData("Value", "Browser Path");

	// prerequisite for test - launch the application and login
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void launchURLAndLogIn() throws Exception {
		if (BROWSER_NAME.equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", BROWSER_PATH);
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.firefox.bin", BROWSER_PATH);
			driver = new FirefoxDriver();
		}
		// Launch application
		driver.manage().deleteAllCookies();
		driver.get(APP_URL);
		driver.manage().window().maximize();
		// check if the application launched successfully
		try {
			Assert.assertEquals(APP_URL, driver.getCurrentUrl());
			Log.info("Application launched successfully");

		} catch (Throwable pageNavigationError) {
			//Log.info("Failed to launch the application");
		}
	}

	@SuppressWarnings("deprecation")
	@AfterMethod
	public void takeScreenshotOnTestFailure(ITestResult result)
			throws IOException {
		// takes screenshot in case of test failure
		if (!(result.isSuccess())) {
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
			Date date = new Date();
			FileUtils.moveFile(
					scrFile,
					new File(root + "\\screenshots\\"
							+ result.getMethod().getMethodName()
							+ dateFormat.format(date) + ".png"));
			Log.info("FAILED - " + result.getMethod().getMethodName());
		}
		// closes the browser quitting the browser instance
		else {
			Log.info("PASSED - " + result.getMethod().getMethodName());
		}
		driver.close();
		//driver.quit();
	}
	
	@AfterSuite
	public void tearDown(){
		driver.quit();
	}
	

	// methods to initialize excel for data source
	// returns the test status value disabled/enabled
	public String getTestRunStatus(String colName, String rowName)
			throws BiffException, IOException {
		testEnableDisable.customizedTestDataDriver(root
				+ "//test-data//DataInput.xls", "TestStatus");
		// load the Excel Sheet Columns in to Dictionary for Further use in our
		// Test cases.
		testEnableDisable.ColumnDictionary();
		// load the Excel Sheet Rows in to Dictionary for Further use in our
		// Test cases.
		testEnableDisable.RowDictionary();
		String testStatus = (testEnableDisable.ReadCell(
				testEnableDisable.GetCol(colName),
				testEnableDisable.GetRow(rowName)));
		return testStatus;
	}

	// returns the data specific to test to be used in test
	public String getTestData(String colName, String rowName)
			throws BiffException, IOException {
		testData.customizedTestDataDriver(root + "//test-data//DataInput.xls",
				"TestData");
		// load the Excel Sheet Columns in to Dictionary for Further use in our
		// Test cases.
		testData.ColumnDictionary();
		// load the Excel Sheet Rows in to Dictionary for Further use in our
		// Test cases.
		testData.RowDictionary();
		String testDataValue = (testData.ReadCell(testData.GetCol(colName),
				testData.GetRow(rowName)));
		return testDataValue;
	}

	// returns the data common to all test
	public String getCommonTestData(String colName, String rowName) {
		try {
			testDataCommon.customizedTestDataDriver(root
					+ "//test-data//DataInput.xls", "TestDataCommon");
			// load the Excel Sheet Columns in to Dictionary for Further use in
			// our
			// Test cases.
			testDataCommon.ColumnDictionary();
			// load the Excel Sheet Rows in to Dictionary for Further use in our
			// Test cases.
			testDataCommon.RowDictionary();
		} catch (Exception e) {

		}
		String testDataCommonValue = (testDataCommon.ReadCell(
				testDataCommon.GetCol(colName), testDataCommon.GetRow(rowName)));
		return testDataCommonValue;
	}

}
