package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class searchTest extends Base {
	SearchPage searchPage;
	HomePage homePage;

	public searchTest() {
		super();
	}
	
	public	 WebDriver driver;

	@BeforeMethod
	public void initialize() {
		driver = initializeAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void finalize() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.validProductSearch());
	}

	@Test(priority = 2)
	public void verifyWithInvalidProduct() {

		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.invalidProductSearch(), dataProp.getProperty("noProductTextResultsSearch"));
	    Assert.fail("Intentionally failed this test.");

	}

}
