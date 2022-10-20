package com.amazon.listeners;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amazon.annotations.FrameworkAnnotation;
import com.amazon.enums.ConfigProperties;
import com.amazon.enums.LogType;
import com.amazon.reports.ExtentReport;
import com.amazon.reports.FrameworkLogger;
import com.amazon.utils.PropertyReader;

/**
 * A Test listener class implementing the TestNG Listener {@link ITestListener}
 * to listen to the Tests run by the Framework.
 * <p>
 * This class is used to generate the Extent reports and log the test run
 * statuses along with screenshots.
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see FrameworkLogger
 * @see ExtentReport
 * @see ConfigProperties
 */
public class ListenerClass implements ITestListener {
	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 * <p>
	 * This method is used to initialize the Extent Report for logging.
	 *
	 * @param context The test context
	 */
	@Override
	public void onStart(ITestContext context) {
		ExtentReport.initReports();
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 * <p>
	 * This method is used to flush the data of each test into Extent report.
	 *
	 * @param context The test context
	 */
	@Override
	public void onFinish(ITestContext context) {
		ExtentReport.flushReports();
	}

	/**
	 * Invoked each time before a test will be started.
	 * <p>
	 * This method is responsible to create a new Test in Extent Report and add the
	 * details of authors and categories related to the Test
	 * 
	 */
	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
		ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).author());
		ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).category());
	}

	/**
	 * Invoked each time a test succeeds.
	 * <p>
	 * This method will log the status pass to the Test in Extent Report along with
	 * the screenshot if configured to true
	 * 
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		if ((boolean) PropertyReader.getPropertValue(ConfigProperties.PASSEDSTEPSCREENSHOT)) {
			FrameworkLogger.log(LogType.PASS_SCREENSHOT, result.getMethod().getMethodName() + " is passed");
		} else {
			FrameworkLogger.log(LogType.PASS, result.getMethod().getMethodName() + " is passed");
		}
	}

	/**
	 * Invoked each time a test fails.
	 * <p>
	 * This method will log the status fail to the Test in Extent Report along with
	 * the screenshot if configured to true
	 * <p>
	 * Also, adds the stack trace of the exception occurred in Extent Report
	 * 
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		if ((boolean) PropertyReader.getPropertValue(ConfigProperties.FAILEDSTEPSCREENSHOT)) {
			FrameworkLogger.log(LogType.FAIL_SCREENSHOT, result.getMethod().getMethodName()
					+ " is failed due to the error - " + result.getThrowable().getMessage());
		} else {
			FrameworkLogger.log(LogType.FAIL, result.getMethod().getMethodName() + " is failed due to the error - "
					+ result.getThrowable().getMessage());
		}
		FrameworkLogger.log(LogType.FAIL, Arrays.toString(result.getThrowable().getStackTrace()));
	}

	/**
	 * Invoked each time a test is skipped.
	 * <p>
	 * This method will log the status skip to the Test in Extent Report along with
	 * the screenshot if set to True in Configuration
	 * 
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */
	@Override
	public void onTestSkipped(ITestResult result) {

		if ((boolean) PropertyReader.getPropertValue(ConfigProperties.SKIPPEDSTEPSCREENSHOT)) {
			FrameworkLogger.log(LogType.SKIP_SCREENSHOT, result.getMethod().getMethodName() + " is skipped");
		} else {
			FrameworkLogger.log(LogType.SKIP, result.getMethod().getMethodName() + " is skipped");
		}
	}

	/**
	 * Invoked each time a method fails but has been annotated with
	 * successPercentage and this failure still keeps it within the success
	 * percentage requested.
	 * <p>
	 * Not implemented as of now
	 * 
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// No implementation as of now
	}

	/**
	 * Invoked each time a test fails due to a timeout.
	 * <p>
	 * Not implemented as of now
	 * 
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 */
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// No implementation as of now
	}
}
