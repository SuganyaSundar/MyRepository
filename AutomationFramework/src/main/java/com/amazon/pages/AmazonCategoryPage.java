package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amazon.enums.LogType;
import com.amazon.enums.WaitStrategy;
import com.amazon.factories.ExplicitWaitFactory;
import com.amazon.reports.FrameworkLogger;
import com.amazon.utils.DynamicXPathUtils;
import com.amazon.utils.PageActions;

/**
 * Page Object Class for the actions performed on Category Page in Amazon page
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see ExplicitWaitFactory
 * @see FrameworkLogger
 */
public class AmazonCategoryPage extends PageActions {

	/**
	 * String variable to store the Xpath of filter element to be selected in
	 * Category page
	 * <p>
	 * Contains 2 replaceable substrings which have to be replaced with the dynamic
	 * values passed from Test case - Filter section name and Filter value
	 */
	private String filterXpath = "//div/span[text()='%filterName%']//parent::div//following-sibling::ul/li/span/a/span[text()='%filterText%']";
	/**
	 * By locator variable to store the element of Results section from Category
	 * page
	 */
	private By sectionResults = By.cssSelector(".s-main-slot");
	/**
	 * By locator variable to store the link of sort dropdown icon
	 */
	private By linkSortIcon = By.xpath("//span[@id='a-autoid-0-announce']");
	/**
	 * String variable to store the Xpath of the link of Sort type element in
	 * Category page
	 * <p>
	 * Contains a replaceable substring which have to be replaced with the dynamic
	 * value of Sort type passed from Test case
	 */
	private String xpathSortType = "//ul[@role='listbox']/li/a[text()='%replaceable%']";
	/**
	 * String variable to store the Xpath of the link of Sorted type text field in
	 * Category page
	 * <p>
	 * Contains a replaceable substring which have to be replaced with the dynamic
	 * value of Sort type passed from Test case
	 */
	private String xpathSortedBy = "//span[@id='a-autoid-0']/span/span/span[text()='%replaceable%']";
	/**
	 * By locator variable to store the image link of second Item from sorted
	 * results in Category page
	 */
	private By xpathSecondItem = By.xpath("//div[@cel_widget_id='MAIN-SEARCH_RESULTS-2']/div/div/div/span/a/div/img");

	/**
	 * By locator variable to store the image link of product from the Product page
	 * opened in new tab
	 */
	private By resultItem = By.id("imgTagWrapperId");

	/**
	 * Method to filter the Category page based on input parameters - Filter section
	 * name and filter value
	 * <p>
	 * Also wait till the filtered results section gets loaded in the page
	 * <p>
	 * Log the status Pass to Framework Logger after filtering the page along with
	 * screenshot 
	 * 
	 * @param filterSection Dynamic value of section name to be filtered which is
	 *                      passed as input from Test case
	 * @param filterValue   Dynamic value of filter text to be selected under the
	 *                      filter section which is passed as input from Test case
	 * @return Current instance of {@link AmazonCategoryPage}
	 */
	public AmazonCategoryPage filterCategoryResults(String filterSection, String filterValue) {
		String newXpath = DynamicXPathUtils.generateXpath(filterXpath, "%filterName%", filterSection);
		newXpath = DynamicXPathUtils.generateXpath(newXpath, "%filterText%", filterValue);

		clickElement(By.xpath(newXpath), WaitStrategy.PRESENCE);

		ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, sectionResults);

		FrameworkLogger.log(LogType.PASS_SCREENSHOT, "Results filtered successfully");

		return this;
	}

	/**
	 * Method to sort the filtered results in Category page based on sort type
	 * <p>
	 * Also wait till the sorting is applied in the results section
	 * <p>
	 * Log the status Pass to Framework Logger after sorting the results along with
	 * screenshot 
	 * 
	 * @param sortType Dynamic value of sorting type to be applied on the results
	 *                 which is passed as input from Test case
	 * @return Current instance of {@link AmazonCategoryPage}
	 */
	public AmazonCategoryPage sortResults(String sortType) {
		clickElement(linkSortIcon, WaitStrategy.CLICKABLE);

		String newXpath = DynamicXPathUtils.generateXpath(xpathSortType, "%replaceable%", sortType);
		clickElement(By.xpath(newXpath), WaitStrategy.VISIBLE);

		newXpath = DynamicXPathUtils.generateXpath(xpathSortedBy, "%replaceable%", sortType);
		ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, By.xpath(newXpath));

		FrameworkLogger.log(LogType.PASS_SCREENSHOT, "Results sorted successfully");
		return this;
	}

	/**
	 * Method to click on second product from filtered results once the image of the
	 * product is visible
	 * <p>
	 * Also, switch to the new tab opened to view the Product and wait till the
	 * product image is visible
	 * <p>
	 * Log the status Pass to Framework Logger after clicking on the second product in
	 * results along with screenshot
	 * 
	 * @return new instance of {@link AmazonProductPage}
	 */
	public AmazonProductPage selectSecondItem() {
		WebElement secondItem = ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, xpathSecondItem);
		String itemName = secondItem.getAttribute("alt");
		
		secondItem.click();
		
		switchWindow();
		
		ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, resultItem);
		
		FrameworkLogger.log(LogType.PASS_SCREENSHOT,"Second Item from filtered results - " + itemName + " opened successfully");
		
		return new AmazonProductPage();
	}
}
