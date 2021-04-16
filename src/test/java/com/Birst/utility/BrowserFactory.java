package com.Birst.utility;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
	
	WebDriver driver;
	
	public static WebDriver launchApplication(WebDriver driver,String browserName , String appUrl) {
		
		switch (browserName) 
		{
		case "ie":
			System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

			break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			
			break;

		case "firefox":
			System.setProperty("webdriver.firefox.driver", "./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();

			break;

		default:
			break;
		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return driver;
	}
	


}
