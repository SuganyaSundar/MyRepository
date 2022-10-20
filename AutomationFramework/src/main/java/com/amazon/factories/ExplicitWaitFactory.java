package com.amazon.factories;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.constants.FrameworkConstants;
import com.amazon.driver.DriverManager;
import com.amazon.enums.WaitStrategy;
import com.amazon.exceptions.WebElementNotFoundException;

/**
 * Factory class to implement the Explicit wait with the input of which wait
 * strategy has to be used
 *
 * @since 05-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see FrameworkConstants
 * @see WaitStrategy
 * @see WebElementNotFoundException
 */

public final class ExplicitWaitFactory {
	/**
	 * Private Constructor to prevent external initialization of the class
	 */
	private ExplicitWaitFactory() {
	}

	/**
	 * Static method to perform Explicit wait operation on the WebElement based on
	 * wait strategy specified in the arguments
	 * <p>
	 * Wait strategy values are being defined in the custom Enum
	 * {@link WaitStrategy}
	 * <p>
	 * In case of any issue with finding web elements using wait, then {@link WebElementNotFoundException} exception will be thrown
	 * 
	 * @param waitStrategy defines the ExpectedCondition to be applied on
	 *                     {@link org.openqa.selenium.support.ui.WebDriverWait}
	 * @param by           refers to the By locator used to find WebElement
	 * @return WebElement returned by WebDriverWait
	 */
	public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by) {
		WebElement element = null;
		try {
			if (waitStrategy == WaitStrategy.CLICKABLE) {
				element = new WebDriverWait(DriverManager.getDriver(),
						Duration.ofSeconds(FrameworkConstants.getEXPLICITWAITTIME()))
						.until(ExpectedConditions.elementToBeClickable(by));
			} else if (waitStrategy == WaitStrategy.PRESENCE) {
				element = new WebDriverWait(DriverManager.getDriver(),
						Duration.ofSeconds(FrameworkConstants.getEXPLICITWAITTIME()))
						.until(ExpectedConditions.presenceOfElementLocated(by));
			} else if (waitStrategy == WaitStrategy.VISIBLE) {
				element = new WebDriverWait(DriverManager.getDriver(),
						Duration.ofSeconds(FrameworkConstants.getEXPLICITWAITTIME()))
						.until(ExpectedConditions.visibilityOfElementLocated(by));
			}
		} catch (Exception e) {
			throw new WebElementNotFoundException("No element found using locator - "+by,e.getCause());
		}

		return element;
	}

}
