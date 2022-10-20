package com.amazon.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.amazon.driver.Driver;
import com.amazon.driver.DriverManager;
import com.amazon.enums.WaitStrategy;
import com.amazon.factories.ExplicitWaitFactory;

/**
 * Utility class to perform basic operations on WebElement such as Click,
 * SendKeys efficiently
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see WaitStrategy
 * @see ExplicitWaitFactory
 */
public class PageActions {

	/**
	 * WebDriver instance containing the WebElement, retrieved from {@link Driver}
	 * class
	 */
	protected WebDriver driver = DriverManager.getDriver();
	/**
	 * Javascript executor declared and initialized from driver instance
	 */
	protected JavascriptExecutor js = (JavascriptExecutor) driver;
	/**
	 * WebElement declared and initialized with null value
	 */
	protected WebElement element = null;

	/**
	 * Method to find WebElement using Wait Strategy passed as input and perform the
	 * click operation
	 * 
	 * @param elementName  By locator of the WebElement which needs to be clicked
	 * @param waitStrategy WaitStrategy to be applied to find the element on the
	 *                     driver instance
	 */
	protected void clickElement(By elementName, WaitStrategy waitStrategy) {
		element = ExplicitWaitFactory.performExplicitWait(waitStrategy, elementName);
		element.click();
	}

	/**
	 * Method to find WebElement using Wait Strategy passed as input and perform the
	 * SendKeys operation
	 * 
	 * @param elementName  By locator of the WebElement on which input text needs to
	 *                     be entered
	 * @param text         Text value to be entered in the WebElement
	 * @param waitStrategy WaitStrategy to be applied to find the element on the
	 *                     driver instance
	 */
	protected void sendKeysElement(By elementName, String text, WaitStrategy waitStrategy) {
		element = ExplicitWaitFactory.performExplicitWait(waitStrategy, elementName);
		element.sendKeys(text);
	}

	/**
	 * Method to perform the action of scrolling the web page until the element
	 * passed as parameter is visible
	 * 
	 * @param elmnt Element to be viewed on the page by scrolling
	 */
	protected void scrollView(WebElement elmnt) {
		js.executeScript("arguments[0].scrollIntoView();", elmnt);
	}

	/**
	 * Method to switch the window to child window once the new tab is opened
	 */
	protected void switchWindow() { 
		Object[] windowHandles=driver.getWindowHandles().stream().toArray();
		driver.switchTo().window(windowHandles[windowHandles.length-1].toString());
	}

	/**
	 * Method to check no. of elements with the By locator passed as parameter
	 * 
	 * @param by Locator of the element to be validated for presence
	 * @return No. of elements with the provided By locator
	 */
	protected int checkNoOfElements(By by) {
		return driver.findElements(by).size();
	}

	/**
	 * Method to retrieve the text value present in the list of Web elements
	 * 
	 * @param by Common Locator of the WebElements to be retrieved
	 * @return List containing the Text values from the Web Elements retrieved
	 */
	protected List<String> getListFromElement(By by) {
		List<WebElement> elementList = driver.findElements(by);
		List<String> listValues = new ArrayList<>();
		elementList.forEach(elmnt -> listValues.add(elmnt.getText()));
		return listValues;
	}
}
