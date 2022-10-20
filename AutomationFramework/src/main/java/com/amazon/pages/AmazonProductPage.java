package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amazon.enums.LogType;
import com.amazon.enums.WaitStrategy;
import com.amazon.factories.ExplicitWaitFactory;
import com.amazon.reports.FrameworkLogger;
import com.amazon.utils.PageActions;

/**
 * Page Object Class for the actions performed on the Product page opened
 * 
 * @since 08-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see ExplicitWaitFactory
 * @see FrameworkLogger
 */
public class AmazonProductPage extends PageActions {
	/**
	 * Locator variable to identify the section 'About this item' from the page
	 * using xPath
	 */
	private By sectionAboutItem = By.xpath("//h1[contains(text(),'About this item')]");
	/**
	 * Locator variable to identify all the list items from 'About this item'
	 * section using xPath
	 */
	private By featureListXpath = By.xpath("//div[@id='feature-bullets']//span[@class='a-list-item']");

	/**
	 * Locator variable to identify the link of Show More in the Product details
	 * section
	 */
	private By xpathShowMore = By.xpath("//span[text()='Show More']");
	/**
	 * Locator variable to identify the link of Show Less in the Product details
	 * section
	 */
	private By xpathShowLess = By.xpath("//span[text()='Show Less']");

	/**
	 * Method to retrieve the details of all features listed under 'About this item'
	 * section of the Product
	 * <p>
	 * If Show More icon is present, then it will be clicked to view hidden features
	 * <p>
	 * Log the status Info to Framework Logger with the list of features
	 * <p>
	 * Log the status Pass to Framework Logger along with screenshot
	 * 
	 * @return Boolean value if element is present
	 */
	public boolean getProductDetails() {
		WebElement aboutItemElement = ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, sectionAboutItem);
		scrollView(aboutItemElement);

		clickElement(xpathShowMore, WaitStrategy.CLICKABLE);

		ExplicitWaitFactory.performExplicitWait(WaitStrategy.CLICKABLE, xpathShowLess);

		FrameworkLogger.logInfoList(getListFromElement(featureListXpath));
		FrameworkLogger.log(LogType.PASS_SCREENSHOT, "Validated the section 'About this item' in Product page");
		
		return aboutItemElement.isDisplayed();
	}
}
