package com.amazon.reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

/**
 * Class implementing Thread Safety mechanism for writing the Extent Test
 * details to Extent Report using ThreadLocal in case of parallel execution of
 * Tests 
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 */
public final class ExtentManager {
	/**
	 * Private Constructor to prevent external Initialization of the class
	 */
	private ExtentManager() {
	}

	/**
	 * ThreadLocal variable of {@link ExtentTest} instances for each thread to have
	 * their own reference
	 */
	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

	/**
	 * Getter method to retrieve the current thread of ExtentTest instance
	 * 
	 * @return ExtentTest instance of current thread
	 */
	static ExtentTest getExtentTest() {
		return extTest.get();
	}

	/**
	 * Setter method to assign the Extent Test reference to current thread of
	 * ExtentTest instance
	 * @param test reference of ExtentTest to be assigned for class variable
	 */
	static void setExtentTest(ExtentTest test) {
		if (Objects.nonNull(test))
			extTest.set(test);
	}

	/**
	 * Static method to remove the current thread of ExtentTest instance
	 */
	public static void unload() {
		extTest.remove();
	}
}
