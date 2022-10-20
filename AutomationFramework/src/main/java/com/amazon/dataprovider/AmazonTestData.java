package com.amazon.dataprovider;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
/**
 * POJO class for Amazon Test Data JSON file
 * 
 * @since 18-Oct-2022
 * @author Suganya Jothiramalingam
 */
@Data
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public class AmazonTestData {
	/**
	 * Variable for value of browser 
	 */
	private String browser;
	/**
	 * Variable for value of browser version 
	 */
	private String version;
	/**
	 * Variable for value of Category to be selected 
	 */
	private String category;
	/**
	 * Variable for value of Sub-Category selected 
	 */
	private String subcategory;
	/**
	 * Variable for value of Filter section 
	 */
	private String filtersection;
	/**
	 * Variable for value of values to be selected under Fiilter section 
	 */
	private String filtervalue;
	/**
	 * Variable for value of sort type to be applied to filter results 
	 */
	private String sorttype;
}
