package com.amazon.utils;
/**
 * Utility class to generate Dynamic XPath values
 * 
 * @since 06-Oct-2022
 * @author Suganya Jothiramalingam
 */
public final class DynamicXPathUtils {
	/**
	 * Private Constructor to prevent external initialization of the class
	 */
	private DynamicXPathUtils() {}
	
	/**
	 * Method to generate Dynamic XPath provided the xpath value and replaceable value
	 * @param xpath String which contains the Xpath with replaceable 
	 * @param text String which needs to be replaced in xpath 
	 * @param value String which needs to put in xpath against the replaceable text
	 * @return Dynamic XPath value with the replaced text
	 */
	public static String generateXpath(String xpath,String text,String value) {
		return xpath.replace(text, value);
	}
}
