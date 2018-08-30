package com.functionalComponents;

import org.testng.Reporter;

public class Report {

	public static void getMessage(String message) {
		Reporter.log(message);
		System.out.println(message);
	}

}
