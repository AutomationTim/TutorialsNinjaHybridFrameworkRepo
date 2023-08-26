package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	// Driver initialization
	
	WebDriver driver;
	
	
	
	// Objects
	
	@FindBy(linkText="HP LP3065")
	WebElement validProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement invalidProduct;
	


	
	//Constructor
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// Actions
	
	public boolean validProductSearch() {
		
	boolean displayStatusOfProductSearch = validProduct.isDisplayed();
	return displayStatusOfProductSearch;
	}
	
	
	public String invalidProductSearch() {
		
		String invalidSearchOfTheProduct = invalidProduct.getText();
		return invalidSearchOfTheProduct;
	}
	
	
}
