package com.amazon.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.amazon.driver.DriverManager;

/**
 * Utility class to handle Screenshot operations in the Framework
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 */
public final class ScreenshotUtils {
	/**
	 * Private Constructor to prevent external Initialization of the class
	 */
	private ScreenshotUtils() {
	}

	/**
	 * Method to take screenshot of the Driver instance in the format
	 * <code>OutputType.BASE64</code>
	 * 
	 * @return String Object in which information about the screenshot is stored
	 */
	public static String getBase64() {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

}
