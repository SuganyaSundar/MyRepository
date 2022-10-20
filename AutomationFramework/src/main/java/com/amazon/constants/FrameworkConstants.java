package com.amazon.constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazon.enums.ConfigProperties;
import com.amazon.utils.PropertyReader;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * Class with the list of Constant variables used in Automation Framework.
 * 
 * @since 05-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 */

public final class FrameworkConstants {

	/**
	 * Private Constructor to prevent external initialization of the class
	 */
	private FrameworkConstants() {
	}

	/**
	 * Constant variable for setting Explicit wait duration
	 */
	@Getter(AccessLevel.PUBLIC)
	private static final int EXPLICITWAITTIME = 10;	

	/**
	 * Constant variable of Amazon Test Data JSON file path
	 */
	@Getter(AccessLevel.PUBLIC)
	private static final String AMAZONJSONFILEPATH ="testdata/AmazonTestData.json";

	
	/**
	 * Constant variable of Extent Report folder path
	 */
	private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-reports/";
	/**
	 * Static variable of Extent Report file path, constructed based on the flag set
	 * in Config.properties file if the report needs to be overridden or not
	 */
	private static String extentReportFilePath = "";

	/**
	 * Getter method for the field Extent Report File Path
	 * <p>
	 * If the field is empty, calls the static method {@link createReportPath()} to
	 * create new path for Extent report file
	 * 
	 * @return String value of the field extentReportFilePath
	 */
	public static String getExtentReportFilePath() {
		if (extentReportFilePath.isEmpty()) {
			extentReportFilePath = createReportPath();
		}
		return extentReportFilePath;
	}

	/**
	 * Method to set the extentReportFilePath based on the flag set in
	 * Config.Properties file
	 * <p>
	 * flag=true --> Existing file in Extent Report folder path will be overridden
	 * <p>
	 * flag=false --> New report will be generated under a new folder with date
	 * 
	 * @return String value set for the field extentReportFilePath
	 */
	private static String createReportPath() {
		if (!(boolean) PropertyReader.getPropertValue(ConfigProperties.OVERRIDEREPORTS)) {
			extentReportFilePath = EXTENTREPORTFOLDERPATH
					+ new SimpleDateFormat("dd-MMM-yy HH-mm").format(new Date(System.currentTimeMillis()))
					+ "/index.html";
		} else {
			extentReportFilePath = EXTENTREPORTFOLDERPATH + "index.html";
		}
		return extentReportFilePath;
	}
}
