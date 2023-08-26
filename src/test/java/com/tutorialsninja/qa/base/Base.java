package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.utilities;

public class Base {
	
	WebDriver driver;
	 public Properties prop;
	 public Properties dataProp;
	
	public  Base() {
		 prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
		
		dataProp = new Properties();
		
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		try {
		FileInputStream dataFis = new FileInputStream(dataPropFile);
		dataProp.load(dataFis);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	
	}

    public WebDriver initializeAndOpenApplicationURL(String broswer) {
	if (broswer.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();

	} else if (broswer.equalsIgnoreCase("edge")) {
		driver = new EdgeDriver();
	}

	else if (broswer.equalsIgnoreCase("firefox")) {
		driver = new FirefoxDriver();
	}

	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utilities.IMPLICIT_WAIT_TIME));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(utilities.PAGE_LOAD_TIME));
	driver.get(prop.getProperty("url"));
	
	return driver;
    }
}
