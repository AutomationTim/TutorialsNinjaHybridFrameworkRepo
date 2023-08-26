package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccssesPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.utilities;

public class RegisterTest extends Base {

	RegisterPage registerPage;
	AccountSuccssesPage accountSuccssesPage;

	public RegisterTest() {
		super();
	}

	public	 WebDriver driver;

	@BeforeMethod
	public void initialize() {

		driver = initializeAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();

	}

	@AfterMethod
	public void finalize() {
		driver.quit();

	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFields() {

		accountSuccssesPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telePhoneNumber"), prop.getProperty("ValidCredentialsPw"),
				prop.getProperty("ValidCredentialsPw"));

		Assert.assertEquals(accountSuccssesPage.seccssesMessage(),
				dataProp.getProperty("accountSuccssfullyCreatedHeading"), "Account succsses message is not displayed");
		


	}

	@Test(priority = 2)
	public void verifyRegisteringAccountWithAllFields() {

		accountSuccssesPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telePhoneNumber"), prop.getProperty("ValidCredentialsPw"),
				prop.getProperty("ValidCredentialsPw"));

		Assert.assertEquals(accountSuccssesPage.seccssesMessage(),
				dataProp.getProperty("accountSuccssfullyCreatedHeading"), "Account succsses message is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisterWithoutFillingAllFields() {

		registerPage.continiueBtn();

		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"),
				dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"),
				dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"),
				dataProp.getProperty("passwordWarning")));

	}

}
