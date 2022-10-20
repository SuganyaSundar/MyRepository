package com.amazon.reports;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.amazon.enums.LogType;
import com.amazon.utils.ScreenshotUtils;

/**
 * Logger class using Consumer Interface feature of Java 8 to log the status to
 * Extent Test in Extent Report
 * 
 * @since 16-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see LogType
 */
public final class FrameworkLogger {
	/**
	 * Private Constructor to prevent external initialization of class
	 */
	private FrameworkLogger() {
	}

	/**
	 * Variable to log Status PASS to Extent Test with the message
	 */
	private static Consumer<String> extentPass = message -> ExtentManager.getExtentTest().pass(message);
	/**
	 * Variable to log Status FAIL to Extent Test with the message
	 */
	private static Consumer<String> extentFail = message -> ExtentManager.getExtentTest().fail(message);
	/**
	 * Variable to log Status INFO to Extent Test with the message
	 */
	private static Consumer<String> extentInfo = message -> ExtentManager.getExtentTest().info(message);
	/**
	 * Variable to log Status SKIP to Extent Test with the message
	 */
	private static Consumer<String> extentSkip = message -> ExtentManager.getExtentTest().skip(message);
	/**
	 * Variable to log Status INFO to Extent Test with the message in Unordered List
	 * format
	 */
	private static Consumer<List<String>> extentInfoList = message -> ExtentManager.getExtentTest()
			.info(MarkupHelper.createUnorderedList(message));
	/**
	 * Variable to log Status Pass to Extent Test with the message along with
	 * screenshot captured using {@link ScreenshotUtils}
	 */
	private static Consumer<String> extentPassScreenshot = message -> ExtentManager.getExtentTest().pass(message,
			MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64()).build());
	/**
	 * Variable to log Status FAIL to Extent Test with the message along with
	 * screenshot captured using {@link ScreenshotUtils}
	 */
	private static Consumer<String> extentFailScreenshot = message -> ExtentManager.getExtentTest().fail(message,
			MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64()).build());
	/**
	 * Variable to log Status INFO to Extent Test with the message along with
	 * screenshot captured using {@link ScreenshotUtils}
	 */
	private static Consumer<String> extentInfoScreenshot = message -> ExtentManager.getExtentTest().info(message,
			MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64()).build());
	/**
	 * Variable to log Status SKIP to Extent Test with the message along with
	 * screenshot captured using {@link ScreenshotUtils}
	 */
	private static Consumer<String> extentSkipScreenshot = message -> ExtentManager.getExtentTest().skip(message,
			MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64()).build());
	/**
	 * Variable to print the message in Console
	 */
	private static Consumer<String> consolePrint = System.out::println;

	/**
	 * Enum Map to store all the key-value pairs for each Enum of {@link LogType}
	 */
	private static Map<LogType, Consumer<String>> map = new EnumMap<>(LogType.class);

	/**
	 * Static block to put all key-value pairs of into Enum Map
	 */
	static {
		map.put(LogType.PASS, extentPass);
		map.put(LogType.FAIL, extentFail);
		map.put(LogType.INFO, extentInfo);
		map.put(LogType.SKIP, extentSkip);
		map.put(LogType.PASS_SCREENSHOT, extentPassScreenshot);
		map.put(LogType.FAIL_SCREENSHOT, extentFailScreenshot);
		map.put(LogType.INFO_SCREENSHOT, extentInfoScreenshot);
		map.put(LogType.SKIP_SCREENSHOT, extentSkipScreenshot);
		map.put(LogType.CONSOLE, consolePrint);
	}

	/**
	 * Method to log the status with the message using Consumer Interface
	 * 
	 * @param status Key for which value of Consumer to be fetched from Enum map
	 * @param message Message to be added to log
	 */
	public static void log(LogType status, String message) {		
		map.get(status).accept(message);		
	}
	/**
	 * Method to log the status Info with the message in List format using Consumer Interface
	 * 
	 * @param message Message to be added to log as INFO
	 */
	public static void logInfoList(List<String> message) {
		extentInfoList.accept(message);
	}
}
