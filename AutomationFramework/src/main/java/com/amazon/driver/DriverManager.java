package com.amazon.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

/**
 * Class implementing Thread Safety mechanism for Driver Instances using
 * ThreadLocal in case of parallel execution
 * 
 * @since 05-Oct-2022
 * @author Suganya Jothiramalingam
 */
public final class DriverManager {
	/**
	 * Private Constructor to prevent external Initialization of the class
	 */
	private DriverManager() {
	}

	/**
	 * ThreadLocal variable of WebDriver instances for each thread to have their own
	 * reference
	 */
	private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

	/**
	 * Getter method to retrieve the current thread of WebDriver instance
	 * 
	 * @return WebDriver instance of current thread
	 */
	public static WebDriver getDriver() {
		return dr.get();
	}

	/**
	 * Setter method to assign the driver value to the current thread of WebDriver
	 * instance
	 * @param driverRef reference to be set for the class variable dr
	 */
	static void setDriver(WebDriver driverRef) {
		if (Objects.nonNull(driverRef))
			dr.set(driverRef);
	}

	/**
	 * Static method to remove the current thread of WebDriver instance
	 */
	static void unload() {
		dr.remove();
	}
}
