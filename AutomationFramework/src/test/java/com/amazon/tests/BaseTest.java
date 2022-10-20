package com.amazon.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.amazon.dataprovider.AmazonTestData;
import com.amazon.driver.Driver;

/**
 * Parent class of all Test classes used for Setup and Tear down actions
 * 
 * @since 08-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 */
public class BaseTest {
	/**
	 * Set Up method to initialize Driver
	 * 
	 * @param data Hashmap data from Data Provider of the Test method
	 */

	@BeforeMethod
	protected void setUp(Object[] data) {
		Driver.initDriver(((AmazonTestData)data[0]).getBrowser(),((AmazonTestData)data[0]).getVersion());
	}

	/**
	 * Tear down method to quit driver
	 */
	@AfterMethod
	protected void tearDown() {
		Driver.quitDriver();
	}
}
