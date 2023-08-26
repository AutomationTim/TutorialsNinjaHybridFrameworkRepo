package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccssesPage {
	
	WebDriver driver;
	
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement succssesHeading;
	
	
	
	public AccountSuccssesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String seccssesMessage() {
	String accountSuccssesPageHeadingText =	succssesHeading.getText();
	return accountSuccssesPageHeadingText;
	}

}
