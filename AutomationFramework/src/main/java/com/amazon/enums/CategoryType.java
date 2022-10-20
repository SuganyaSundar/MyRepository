package com.amazon.enums;

import com.amazon.annotations.FrameworkAnnotation;

/**
 * Custom Enum with the list of Category types to be added to Framework Annotation if used in any Test methods
 * 
 * @since 05-Oct-2022
 * @author Suganya Jothiramalingam
 * @see FrameworkAnnotation
 */
public enum CategoryType {
	SMOKE,
	REGRESSION,
	SANITY,
	E2E;
}
