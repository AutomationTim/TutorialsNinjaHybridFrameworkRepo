package com.tutorialsninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.utilities;

public class RegisterPage {

	WebDriver driver;

	// Elements

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement newsletterCheckbox;

	@FindBy(name = "agree")
	private WebElement agreeCheckbox;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continiueButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarningMessage;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningMessage;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarningMessage;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningMessage;

	// Constructor

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAddress(String emailAddressText) {
		emailField.sendKeys(emailAddressText);

	}

	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);

	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);

	}

	public void confirmPassword(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);

	}

	public void newsletterCheckbox() {
		newsletterCheckbox.click();

	}

	public void agreeCheckbox() {
		agreeCheckbox.click();

	}

	public AccountSuccssesPage continiueBtn() {
		continiueButton.click();
		return new AccountSuccssesPage(driver);

	}

	public String privacyPolicyWarningMessage() {
		String privacyPolicyMsg = privacyPolicyWarningMessage.getText();
		return privacyPolicyMsg;
	}

	public String firstNameWarningMessage() {
		String firstNameWrning = firstNameWarningMessage.getText();
		return firstNameWrning;
	}

	public String lastNameWarningMessage() {
		String lastNameWrning = lastNameWarningMessage.getText();
		return lastNameWrning;
	}

	public String emailWarningMessage() {
		String emailWarning = emailWarningMessage.getText();
		return emailWarning;
	}

	public String telephoneWarningMessage() {
		String telephoneWarning = telephoneWarningMessage.getText();
		return telephoneWarning;
	}

	public String passwordWarningMessage() {
		String passwordWarnning = passwordWarningMessage.getText();
		return passwordWarnning;
	}

	public AccountSuccssesPage registerWithMandatoryFields(String firstNameText, String lastNameText,
			String emailAddressText, String telephoneText, String passwordText, String confirmPasswordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailAddressText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(confirmPasswordText);
		agreeCheckbox.click();
		continiueButton.click();
		return new AccountSuccssesPage(driver);

	}

	public AccountSuccssesPage registerWithAllFields(String firstNameText, String lastNameText, String emailAddressText,
			String telephoneText, String passwordText, String confirmPasswordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailAddressText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(confirmPasswordText);
		newsletterCheckbox.click();
		agreeCheckbox.click();
		continiueButton.click();
		return new AccountSuccssesPage(driver);

	}

	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarningMessage,
			String expectedFirstNameWarning, String expectedLastNameWarningMessage, String expectedEmailWarningMessage,
			String expectedTelephoneWarningMessage, String expectedPasswordWarningMessage) {

		boolean privacyPolicyWarningStatus = privacyPolicyWarningMessage.getText()
				.contains(expectedPrivacyPolicyWarningMessage);

		boolean firstNameWarrningStatus = firstNameWarningMessage.getText().equals(expectedFirstNameWarning);

		boolean lastNameWarningStatus = lastNameWarningMessage.getText().equals(expectedLastNameWarningMessage);

		boolean emailWarningStatus = emailWarningMessage.getText().equals(expectedEmailWarningMessage);

		boolean telephoneWarningStatus = telephoneWarningMessage.getText().equals(expectedTelephoneWarningMessage);

		boolean passwordWarningStatus = passwordWarningMessage.getText().equals(expectedPasswordWarningMessage);

		return privacyPolicyWarningStatus && firstNameWarrningStatus && lastNameWarningStatus && emailWarningStatus
				&& telephoneWarningStatus && passwordWarningStatus;

	}

}
