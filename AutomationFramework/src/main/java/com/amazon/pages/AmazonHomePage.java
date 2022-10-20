package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.driver.DriverManager;
import com.amazon.enums.LogType;
import com.amazon.enums.WaitStrategy;
import com.amazon.factories.ExplicitWaitFactory;
import com.amazon.reports.FrameworkLogger;
import com.amazon.utils.PageActions;

/**
 * Page Object Class for the actions performed on Home Page in Amazon
 * Website 
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see ExplicitWaitFactory
 * @see FrameworkLogger
 */
public class AmazonHomePage extends PageActions {

	/**
	 * WebElement variable to store the element of Hamburger Menu icon from
	 * Amazon Home page
	 * <p>
	 * Instantiated using Page Factory
	 */
	@FindBy(id = "nav-hamburger-menu")
	private WebElement hamburgerMenu;

	/**
	 * By locator variable to store the element of the Hamburger menu list
	 */
	private By hamburgerMenuList = By.id("hmenu-content");

	/**
	 * Public constructor to instantiate the Page Factory elements in the class
	 */
	public AmazonHomePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	/**
	 * Method to click on the Hamburger Menu element in Amazon Home page
	 * <p>
	 * Also, wait till the Hamburger Menu list is visible in the page post the click
	 * action
	 * <p>
	 * Log the status Pass to Framework Logger after performing the click action along
	 * with screenshot 
	 * 
	 * @return new instance of {@link AmazonHamburgerMenuPage}
	 */
	public AmazonHamburgerMenuPage clickHamburgerMenu() {
		hamburgerMenu.click();
		ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, hamburgerMenuList);
		FrameworkLogger.log(LogType.PASS_SCREENSHOT, "Hamburger Menu clicked");
		return new AmazonHamburgerMenuPage();
	}
}
