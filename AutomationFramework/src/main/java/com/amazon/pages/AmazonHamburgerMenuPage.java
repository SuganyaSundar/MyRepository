package com.amazon.pages;

import org.openqa.selenium.By;

import com.amazon.enums.LogType;
import com.amazon.enums.WaitStrategy;
import com.amazon.factories.ExplicitWaitFactory;
import com.amazon.reports.FrameworkLogger;
import com.amazon.utils.DynamicXPathUtils;
import com.amazon.utils.PageActions;

/**
 * Page Object Class for the actions performed on Hamburger Menu Page in
 * Amazon Website 
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see ExplicitWaitFactory
 * @see FrameworkLogger
 */
public class AmazonHamburgerMenuPage extends PageActions {
	/**
	 * String variable to store the Xpath of category link to be
	 * selected from Hamburger Menu
	 * <p>
	 * Contains the replaceable substring which has to be replaced with the dynamic
	 * category value passed from Test case
	 */
	private String linkCategory = "//div[text()='%replaceable%']";
	/**
	 * String variable to store the Xpath of sub-category link to be
	 * selected from Hamburger Menu
	 * <p>
	 * Contains the replaceable substring which has to be replaced with the dynamic
	 * sub-category value passed from Test case
	 */
	private String linkSubCategory = "//a[text()='%replaceable%']";
	/**
	 * By locator variable to store the element of the link Back to Main
	 * menu
	 */
	private By linkMainMenu = By.cssSelector("ul.hmenu-translateX>li>a.hmenu-back-button");

	/**
	 * Method to click on the Category link from Hamburger Menu in Amazon Website
	 * <p>
	 * Also wait till the Sub-category page is loaded with the link to navigate back
	 * to Main menu
	 * <p>
	 * Log the status Pass to Framework Logger after selecting the Category along with
	 * screenshot 
	 * 
	 * @param categoryText Dynamic value of category name to be selected which is
	 *                     passed as input from Test case
	 * @return Current Class instance
	 */
	public AmazonHamburgerMenuPage clickCategory(String categoryText) {
		String newXpath = DynamicXPathUtils.generateXpath(linkCategory, "%replaceable%", categoryText);
		clickElement(By.xpath(newXpath), WaitStrategy.PRESENCE);
		ExplicitWaitFactory.performExplicitWait(WaitStrategy.CLICKABLE, linkMainMenu);
		FrameworkLogger.log(LogType.PASS_SCREENSHOT, "Category selected");
		return this;
	}

	/**
	 * Method to click on the Sub-Category link after selecting the Category from
	 * Hamburger Menu in Amazon Website
	 * <p>
	 * Log the status Pass to Framework Logger after selecting the SubCategory along
	 * with screenshot 
	 * 
	 * @param subCategoryText Dynamic value of sub-category name to be selected
	 *                        which is passed as input from Test case
	 * @return New instance of {@link AmazonCategoryPage}
	 */
	public AmazonCategoryPage clickSubCategory(String subCategoryText) {
		String newXpath = DynamicXPathUtils.generateXpath(linkSubCategory, "%replaceable%", subCategoryText);
		clickElement(By.xpath(newXpath), WaitStrategy.PRESENCE);
		FrameworkLogger.log(LogType.PASS_SCREENSHOT, "Sub category selected");
		return new AmazonCategoryPage();
	}
}
