package com.amazon.factories;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.amazon.enums.ConfigProperties;
import com.amazon.exceptions.BrowserInvocationFailureException;
import com.amazon.utils.PropertyReader;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory class to implement browser invocation handling both remote and local
 * runs
 *
 * @since 05-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see ConfigProperties
 * @see MalformedURLException
 */
public final class DriverFactory {
	/**
	 * Private Constructor to prevent external initialization of the class
	 */
	private DriverFactory() {
	}

	/**
	 * Method to initialize the driver based on the configuration of remote/local
	 * run
	 * <ul>
	 * <li>If set to remote, the driver is initialized using {@link RemoteWebDriver}
	 * and the desired capabilities are set based on the parameters</li>
	 * <li>If set to local, the driver is initialized using {@link WebDriverManager}</li>
	 * <li>Else, throw {@link BrowserInvocationFailureException} with the message as invalid runmode value</li>
	 * </ul>
	 * 
	 * @param browserName Name of browser which needs to be invoked
	 * @param version     Version of browser
	 * @return Driver instance created for the remote/local setup
	 * @throws MalformedURLException Exception related to Remote WebDriver url access
	 */
	public static WebDriver getDriver(String browserName, String version) throws MalformedURLException {
		WebDriver driver = null;
		String runMode = PropertyReader.getPropertValue(ConfigProperties.RUNMODE).toString();

		DesiredCapabilities cap = new DesiredCapabilities();
		if (runMode.equalsIgnoreCase("remote")) {
			cap.setBrowserName(browserName);
			cap.setVersion(version);
			cap.setPlatform(Platform.ANY);
			cap.setCapability("enableVNC", true);
			cap.setCapability("enableVideo", true);
			cap.setCapability("name", browserName + "_" + version);
			cap.setCapability("videoName", browserName + "_" + version + ".mp4");
			cap.setCapability("timeZone", "Asia/Calcutta");
			driver = new RemoteWebDriver(
					new URL(PropertyReader.getPropertValue(ConfigProperties.REMOTEDRIVERURL).toString()), cap);
		} else if (runMode.equalsIgnoreCase("local")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions opt = new ChromeOptions();
				opt.setBrowserVersion(version);
				driver = new ChromeDriver(opt);
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions opt = new FirefoxOptions();
				opt.setBrowserVersion(version);
				driver = new FirefoxDriver(opt);
			}
		} else {
			throw new BrowserInvocationFailureException("Invalid runmode value in Config file. Please check.");
		}
		return driver;
	}
}
