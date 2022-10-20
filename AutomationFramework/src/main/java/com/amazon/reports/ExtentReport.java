package com.amazon.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.amazon.constants.FrameworkConstants;
import com.amazon.enums.CategoryType;

/**
 * Class for instantiating Extent Report file and writing data of all the
 * run tests details to the report.
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 *
 */
public final class ExtentReport {
	/**
	 * Private Constructor to prevent external Initialization of the class
	 */
	private ExtentReport() {
	}

	/**
	 * Variable to declare and initialize ExtentReports as a single copy to
	 * be shared by all instances of the class
	 */
	private static ExtentReports extent;

	/**
	 * Method to initialize and create the Extent Report in the format of
	 * {@link ExtentSparkReporter} (HTML) in the location configured in Framework
	 * <p>
	 * Also, make the required alterations to the report created such as setting
	 * Theme, Title, Report name.
	 */
	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter report = new ExtentSparkReporter(
					new File(FrameworkConstants.getExtentReportFilePath()));
			extent.attachReporter(report);

			report.config().setTheme(Theme.STANDARD);
			report.config().setDocumentTitle("Suganya Extent Report");
			report.config().setReportName("Testing Report");
		}
	}

	/**
	 * Method to flush the test information from the started reporters to the output
	 * view of HTML
	 * <p>
	 * Also, once data is flushed to the report
	 * <ul>
	 * <li>Current thread of ExtentTest instance is removed using
	 * {@link ExtentManager}</li>
	 * <li>Extent report created is opened in the default browser window for the
	 * users to view</li>
	 * </ul>
	 */
	public static void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to create an Extent Test for the Test case passed as parameter using
	 * {@link ExtentManager}
	 * 
	 * @param testCaseName Name of the test case for which the Extent Test instance
	 *                     needs to be created
	 */
	public static void createTest(String testCaseName) {
		ExtentManager.setExtentTest(extent.createTest(testCaseName));
	}

	/**
	 * Method to assign authors to the Extent Test created
	 * 
	 * @param authors List of authors to be assigned to the ExtentTest
	 */
	public static void addAuthors(String[] authors) {
		for (String temp : authors) {
			ExtentManager.getExtentTest().assignAuthor(temp);
		}
	}

	/**
	 * Method to assign categories to the Extent Test created
	 * @param categories List of categories to be assigned to the ExtentTest
	 */
	public static void addCategories(CategoryType[] categories) {
		for (CategoryType temp : categories) {
			ExtentManager.getExtentTest().assignCategory(temp.toString());
		}
	}
}
