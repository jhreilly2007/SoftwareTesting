//
// Adventure Web Test Demo 
//	@author Janice Reilly	
//			Assignment Software Tesing
//	Additional Libraries: selenium-server-standalone-3.141.5.jar & chromedriver.exe
//
package JR;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

// Works under windows 10 with:
// chromedriver.exe - 2.41.578737 
// chromium - 58.0.2988.0 64-bit 
// selenium - 3.141.5
// testng - 7.0.0
//

public class AdventureWebTest {

	WebDriver driver;
	Wait<WebDriver> wait;
	
	String url="https://webcourse.cs.nuim.ie/~sbrown/cs608/adventure/adventure.html";

	@BeforeClass
	public void setupDriver() throws Exception {
		try {
			System.setProperty("webdriver.chrome.driver","./selenium/chromedriver.exe");
			driver = new ChromeDriver();
			wait = new WebDriverWait( driver, 5 ); // default timeout of 5 seconds on a wait
			driver.get( url );
		} catch (Exception ex) {
			System.out.println("Exception "+ex.toString());
			if (driver!=null)
				driver.quit();
			throw(ex);
		}
	}

	@AfterClass
	public void shutdown() {
		driver.quit();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
	//NOTE: //I could use the after method to return to MAIN PAGE for ALL cases by
			//Excluding the if statement and
			//Deleting all line 145-156 (inclusive) from demoTest()
			//As a Tester Developer I chose to use ITestResult interface for this assignment
			//However In a real environment assessing the if statement would cost time over multiple tests
	
		//After FAILED Test return to MAIN PAge before staring next test
	   if (result.getStatus() == ITestResult.FAILURE ) {
		   
			// Confirm we are still on result page--additional precaution
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
			
			//Click on the 'here link to return to main
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("here")));
			driver.findElement(By.linkText("here")).click();

			//Return & Confirm we are back on main page
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("intro")));

	  }
	}
	 
	@DataProvider(name="Check Discount")
	public Object[][] getEVCdata() {
		return new Object[][] {
			{ "T1", 	"12", 	true, 	"10% discount applies for this trip" },
			{ "T2", 	"35", 	true, 	"20% discount applies for this trip" },
			{ "T3", 	"75", 	true, 	"35% discount applies for this trip" },
			{ "T4", 	"50", 	false, 	"Sorry, but only members get a discount on this trip" },
			{ "T5",    "age",	true, 	"ERROR: Invalid age entered" },
			{ "T6",   "2000", 	true, 	"ERROR: Invalid age entered" },
			{ "T7",  "age10",	false, 	"ERROR: Invalid age entered" },
			{ "T8",    "-10",	false, 	"ERROR: Invalid age entered" }
		};
	}

	@Test(timeOut=60000, dataProvider="Check Discount")
	public void demoTest(String tid, String age, boolean member, String result) {
		
		//***NOTE: For demonstrative purposes I have entered more page checks than are unnecessary. 
		//Any one or a combination will work to ensure the correct page is being tested.
		
		//Wait for MAIN PAGE to load by presence of unique id = "intro"
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("intro")));
		//click 'here' link
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("details")));
		driver.findElement(By.id("details")).click();
		
		//Wait for DETAILS PAGE to load --<legend> text equals "Applicant Details"
		//***NOTE: Using the xpath combined with the getAttribute("textContent") on stored legend to access text
		WebElement legend = driver.findElement(By.tagName("legend"));
		assertEquals(legend.getAttribute("textContent"), "Applicant Details");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/form/fieldset/legend/b[./text() = '"+legend.getAttribute("textContent")+"']")));
		
		//Enter Age by assessing element by unique <input> id "age"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("age")));
		driver.findElement(By.id("age")).sendKeys(age);
		
		// Check member checkbox by unique <input>id "member"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member")));
		if (driver.findElement(By.id("member")).isSelected()!= member)
			driver.findElement(By.id("member")).click();
		
		// Click on submit
		//Accessing input through exact xpath and text value assigned
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/form/fieldset/input[@value='Click Here']")));
		driver.findElement(By.xpath("//*[@id=\"form\"]/form/fieldset/input[@value='Click Here']")).click();
		
		//Wait for RESULTS PAGE to load -- <legend> text equals "Adventure Trip Discount"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"result\"]/fieldset/legend/b[./text() = 'Adventure Trip Discount']")));
			//As opposed to typing in the actual text I could use xpath & getAttribute("textContent") on stored legend.
			//could use: 
			//WebElement legend2 = driver.findElement(By.xpath("//*[@id=\"result\"]/fieldset/legend/b"));
			//assertEquals(legend2.getAttribute("textContent"), "Adventure Trip Discount");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"result\"]/fieldset/legend/b[./text() = '"+legend2.getAttribute("textContent")+"']")));
		
		// Check the discount result <p> id "discount"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("discount")));
		assertEquals(driver.findElement(By.id("discount")).getText(), result);

		// Confirm we are still on result page--additional precaution
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
		
		//Click on the 'here link to return to main
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("here")));
		driver.findElement(By.linkText("here")).click();
			//could use: 
			//driver.get("<a href='adventure.html'>here</a>");

		//Return & Confirm we are back on MAIN PAGE
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("intro")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("details")));
	}

}
