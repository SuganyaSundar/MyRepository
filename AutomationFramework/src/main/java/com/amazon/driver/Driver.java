package com.amazon.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

import com.amazon.enums.ConfigProperties;
import com.amazon.exceptions.BrowserInvocationFailureException;
import com.amazon.factories.DriverFactory;
import com.amazon.utils.PropertyReader;

/**
 * Class containing methods to initialize and quit Driver
 * 
 * @since 05-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see DriverManager
 * @see DriverFactory
 */
public class Driver {
	/**
	 * Private Constructor to prevent external initialization of class
	 */
	Driver() {

	}

	/**
	 * Static variable to declare the WebDriver
	 */
	public static WebDriver driverTest;

	/**
	 * Getter method for the field driver
	 * 
	 * @return Current instance of the WebDriver
	 */
	public static WebDriver getDriver() {
		return driverTest;
	}

	/**
	 * Method to initialize driver using WebDriverManager using parameters -
	 * browserName and version by invoking Driver Factory.
	 * <p>
	 * Once initialized, the driver instance is passed onto {@link DriverManager}
	 * class which is used for Thread management of Driver instances.
	 * <p>
	 * Using driver from {@link DriverManager}, browser is opened with url
	 * configured in Config.properties file
	 * <p>
	 * In case of any exceptions occurring with Driver initialization, custom
	 * exception of @link {@link BrowserInvocationFailureException} is thrown at
	 * runtime
	 * 
	 * @param browserName Parameter to decide which browser needs to be set for the
	 *                    driver.
	 * @param version     Parameter to decide which version needs to be set for the
	 *                    browser in the driver
	 */
	public static void initDriver(String browserName, String version) {
		if (Objects.isNull(DriverManager.getDriver())) {
			try {
				DriverManager.setDriver(DriverFactory.getDriver(browserName, version));
			} catch (Exception e) {
				throw new BrowserInvocationFailureException(e.getMessage());
			}
			driverTest = DriverManager.getDriver();
			driverTest.manage().window().maximize();
			driverTest.get(PropertyReader.getPropertValue(ConfigProperties.AMAZONURL).toString());
		}
	}

	/**
	 * Method to quit the driver and remove Driver instance from
	 * {@link DriverManager}
	 */
	public static void quitDriver() {
		if (Objects.nonNull(driverTest)) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}
}
