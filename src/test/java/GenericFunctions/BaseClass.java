package GenericFunctions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Birst.utility.BrowserFactory;
import com.Birst.utility.ExcelDataProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {

	public static WebDriver driver;
	public ExcelDataProvider excel;
	public static WebDriverWait wait;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() {
		excel = new ExcelDataProvider();

		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/BirstReport.html"));
		report = new ExtentReports();
		report.attachReporter(extent);
	}

	@BeforeClass
	public void setup() {

		driver = BrowserFactory.launchApplication(driver, "chrome", "https://www.google.com");
		wait = new WebDriverWait(driver, 30);
	}

	public static boolean ScrollDownToElement(WebElement xpath) {
		boolean status = true;
		if (status) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			Actions acc = new Actions(driver);

			acc.moveToElement(xpath).click().perform();

		}
		return status;
	}

	public void SelectDropDown(int i, String value) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//select)[" + i + "]")));
		WebElement sel = driver.findElement(By.xpath("(//select)[" + i + "]"));
		Select drpval = new Select(sel);
		drpval.selectByVisibleText(value);
		String clsVal = sel.getAttribute("class");
		// checking the class for specific value
		if (clsVal.contains("isActive")) {
			System.out.println("select dropdown is selected with " + value);
		} else {
			System.out.println("select dropdown is not selected with " + value);
		}
	}

	public static String captureScreenShot(WebDriver driver) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotpath = System.getProperty("user.dir") + "/Screenshots/BirstReport.png";
		try {

			FileHandler.copy(src, new File(screenshotpath));
			System.out.println("Screenshot captured");
		} catch (IOException e) {

			System.out.println("Failed to capture Screenshot" + e.getMessage());
		}
		return screenshotpath;
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		}
		report.flush();
	}

	@AfterClass
	public void tearDown() {
		try {
			TimeUnit.MILLISECONDS.sleep(1500L);
		} catch (InterruptedException e) {

		}
		driver.quit();
	}
}
