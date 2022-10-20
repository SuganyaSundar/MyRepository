package com.amazon.enums;

import com.amazon.reports.FrameworkLogger;

/**
 * Custom Enum with the list of log types to be added to Framework Logger for logging any status
 * 
 * @since 15-Oct-2022
 * @author Suganya Jothiramalingam
 * @see FrameworkLogger
 */
public enum LogType {
	PASS, FAIL, SKIP, INFO, CONSOLE,INFO_LIST,
	PASS_SCREENSHOT,FAIL_SCREENSHOT, SKIP_SCREENSHOT, INFO_SCREENSHOT;
}
