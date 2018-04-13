import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.jna.Platform;


public class SignInTest {
	
	@Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws InterruptedException {
    	setDriverPath();
    	WebDriver driver = new ChromeDriver();
    	driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.get("https://www.cleartrip.com/");
		driver.findElement(By.linkText("Your trips")).click();
	    driver.findElement(By.id("SignIn")).click();
	    Thread.sleep(3000);
	    
	    WebElement iframeSwitch = driver.findElement(By.id("modal_window"));
		driver.switchTo().frame(iframeSwitch);
		Thread.sleep(5000);
		System.out.println("Switched");
		WebElement signElement = driver.findElement(By.id("signInButton"));
		signElement.click();
		Thread.sleep(5000);
		String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void setDriverPath() {
        if (Platform.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (Platform.isWindows()) {
        	System.setProperty("webdriver.chrome.driver", "D:\\Ashok-Data\\Ashok\\Data\\Testing\\chromedriver_win32\\chromedriver.exe");
        }
        if (Platform.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
