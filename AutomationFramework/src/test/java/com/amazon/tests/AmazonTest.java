package com.amazon.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.annotations.FrameworkAnnotation;
import com.amazon.dataprovider.AmazonTestData;
import com.amazon.dataprovider.DataProviderTest;
import com.amazon.enums.CategoryType;
import com.amazon.pages.AmazonHomePage;
import com.amazon.pages.AmazonProductPage;

/**
 * Test class containing all the tests related to the Amazon Coding Challenge
 * 
 * @since 06-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see FrameworkAnnotation
 * @see DataProviderTest
 */
public final class AmazonTest extends BaseTest {
	/**
	 * Private Constructor to prevent external Initialization of the class
	 */
	private AmazonTest() {
	}

	/**
	 * Test method to perform the following actions in the Amazon page
	 * <ul>
	 * <li>Select Category and Sub Category</li>
	 * <li>Filter the results based on filter value</li>
	 * <li>Sort the filtered results based on sort type</li>
	 * <li>Select the second item from sorted results</li>
	 * <li>Assert that “About this item” section is present</li>
	 * <li>Retrieve Product details from 'About this Item' section</li>
	 * </ul>
	 * <p>
	 * All input values being retrieved from Data Provider assigned at runtime from
	 * Test data Json file
	 * 
	 * @param data {@link AmazonTestData} object retrieved from DataProvider
	 */
	@FrameworkAnnotation(author = { "Suganya" }, category = { CategoryType.REGRESSION, CategoryType.SMOKE })
	@Test(dataProvider = "getAmazonJSONDataSupplier", dataProviderClass = DataProviderTest.class)
	public void test1(AmazonTestData data) {
		AmazonProductPage productPage = new AmazonHomePage().clickHamburgerMenu().clickCategory(data.getCategory())
				.clickSubCategory(data.getSubcategory())
				.filterCategoryResults(data.getFiltersection(), data.getFiltervalue()).sortResults(data.getSorttype())
				.selectSecondItem();
		Assert.assertTrue(productPage.getProductDetails());
	}
}
