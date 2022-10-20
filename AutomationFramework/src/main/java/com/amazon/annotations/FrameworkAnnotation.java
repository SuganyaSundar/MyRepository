package com.amazon.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.amazon.enums.CategoryType;
import com.aventstack.extentreports.model.Category;

/**
 * Custom Annotation created in the Framework Structure for adding Authors and Categories to Test Methods
 * 
 * @since 05-Oct-2022
 * @author Suganya Jothiramalingam
 * @see CategoryType
 */
@Retention(RUNTIME)
@Target(METHOD)
@Documented

public @interface FrameworkAnnotation {
	/**
	 * Array containing list of Authors to be assigned for the Test Method. Accepts any number of String values.
	 * @return String object array 
	 */
	public String[] author();
	/**
	 * Array containing list of Categories to be assigned for the Test Method. Accepts any number of Category values from Enum 
	 * {@link CategoryType}
	 * @return Object array of type {@link CategoryType}
	 */
	public CategoryType[] category();
}
