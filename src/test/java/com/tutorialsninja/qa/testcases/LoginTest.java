package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.utilities;

public class LoginTest extends Base {

	LoginPage loginPage;

	public LoginTest() {
		super();
	}

public	 WebDriver driver;

	@BeforeMethod
	public void initialize() {

		driver = initializeAndOpenApplicationURL(prop.getProperty("browserName"));

		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();

	}

	@AfterMethod
	public void finalizeTest() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String Email1, String Password1) {

		AccountPage accountPage = loginPage.login(Email1, Password1);
		Assert.assertTrue(accountPage.getDisplaysStatusOfEditYourAccountInfoOption());
	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		loginPage.login(utilities.generateEmailWithTimeStamp(), prop.getProperty("ValidCredentialsPw"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordWrningMessageText()
				.contains(dataProp.getProperty("emailPasswordNoMatchWarning")));

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {

		loginPage.login(utilities.generateEmailWithTimeStamp(), prop.getProperty("ValidCredentialsPw"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordWrningMessageText()
				.contains(dataProp.getProperty("emailPasswordNoMatchWarning")));

	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidPasswordAndValidEmail() {

		loginPage.login(prop.getProperty("ValidCredentialsEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordWrningMessageText()
				.contains(dataProp.getProperty("emailPasswordNoMatchWarning")));

	}

	@Test(priority = 5)
	public void verifyLoginWithoutAnyCredentials() {

		loginPage.clickOnLoginButton();
		Assert.assertTrue(
				loginPage.retrieveEmailPasswordWrningMessageText()
						.contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
				"Warning: No match for E-Mail Address and/or Password.");

	}

}
