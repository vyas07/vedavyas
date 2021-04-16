package com.Birst.Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Birst.utility.ExcelDataProvider;

import GenericFunctions.BaseClass;

public class BirstFilterBy_Resources extends BaseClass {
	WebDriver driver;

	public BirstFilterBy_Resources(WebDriver ldriver) {

		this.driver = ldriver;
	}

	@FindBy(how = How.NAME, using = "q")
	public static WebElement googlesearchinput;

	@FindBy(how = How.XPATH, using = "//input[@value='Google Search']")
	public static WebElement googleseDDarchinput;

	@FindBy(how = How.XPATH, using = "//div/a/h3")
	public static List<WebElement> gettitle;

	@FindBy(how = How.XPATH, using = "//nav/a/img[contains(@class,'hideScrolled')]")
	public static WebElement getPageLogo;

	@FindBy(how = How.XPATH, using = "//button[text()='Accept All Cookies']")
	public static WebElement AcceptCookies;

	@FindBy(how = How.XPATH, using = "(//li/a[text()='Resources'])[3]")
	public static WebElement Resources;

	@FindBy(how = How.XPATH, using = "//div[normalize-space(text())='Filter By:']")
	public static WebElement xpath;

	@FindBy(how = How.XPATH, using = "//a[normalize-space(text())='Resources Overview']")
	public static WebElement pagecheck;

	@FindBy(how = How.XPATH, using = "//div[text()='Past Webinars']/following-sibling::a")
	public static List<WebElement> VerifyWebinarName;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'designed for the digital economy')]")
	public static WebElement VerifyActualWebinarName;

	
	// This method is used for verifying Google page title
	public void VerifyGoogleTitle() {
		 String titl = driver.getTitle();
		  System.out.println("page title Is : "+titl);
		  Assert.assertEquals("Google",titl);
		  if(titl.equals("Google")) {
			  System.out.println("Page title has been verified and its "+driver.getTitle());  	  
		  }else {
			  System.out.println("Failed to verify Page title");
		  }
	}
	// This method used is for Google search on birst.com
	public void GoogleSearch(String CompanyDomain) {

		googlesearchinput.click();
		googlesearchinput.clear();
		googlesearchinput.sendKeys(CompanyDomain);
		googlesearchinput.submit();

	}

	// This method is used for Searching for title of Birst and seleccting it
	public void GetBirstTitle() {
		String searchTerm = "Birst | Business intelligence and analytics | Infor";
		try {
			TimeUnit.MILLISECONDS.sleep(1000L);
		} catch (InterruptedException e) {

		}
		for (WebElement checktext : gettitle) {
			// System.out.println(checktext.getText());
			String VerifyText = checktext.getText();
			if (searchTerm.equalsIgnoreCase(VerifyText)) {
				checktext.click();
				System.out.println(searchTerm + "has been found");
				break;
			} else {
				System.out.println(searchTerm + "Does not exist");
			}
		}
	}

	// This method is used for accepting cookies from website
	public void AcceptCookies() {
		// Create object of WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, 20);

		// Wait till the element is not visible

		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Accept All Cookies']")));
		if (AcceptCookies.isDisplayed()) {
			AcceptCookies.click();
			System.out.println("AcceptCookies is displayed and clicked on the page");
		} else {

			System.out.println("AcceptCookies is not displayed and clicked on the page");
		}

	}

	// This method is used for verifying Infor logo on homepage
	public void VerifyLogoExistOnPage() {

		if (getPageLogo.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Infor logo is displayed on the page");
		} else {
			Assert.assertTrue(false);
			System.out.println("Infor logo is not displayed on the page");
		}
	}

	// This method is used for navigating to resources
	public void NavigateToResources() {
		if (Resources.isDisplayed()) {
			Resources.click();
			Assert.assertTrue(true);
			System.out.println("Resources has been clicked sucessfully");
		} else {
			Assert.assertTrue(false);
			System.out.println("Failed to click Resources");
		}

	}

	// This method is used for verifying whether resource page is loaded
	public void VerifyResourcePageLoad() {

		// Wait till the element is visible
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[normalize-space(text())='Resources Overview']")));
		if (pagecheck.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Resources page has been loaded sucessfully");
		} else {
			Assert.assertTrue(false);
			System.out.println("Failed to load Resources page");
		}
	}

	// This method is used for scrolling to filterby option page
	public void FilterBy() {
		ScrollDownToElement(xpath);

	}

	// This method is used for verifying webinar name
	public void VerifyWebinar() {
		String WebinarName = "Six steps to becoming a data-driven organization";
		ScrollDownToElement(VerifyActualWebinarName);
		for (WebElement checktext : VerifyWebinarName) {
			String VerifyText = checktext.getText();
			if (WebinarName.equalsIgnoreCase(VerifyText)) {
				System.out.println("VERIFIED " + WebinarName + " has been found");
				break;
			}

		}
		System.out.println("HTML Report has been Generated for this testcase under Reports folder");
	}

}
