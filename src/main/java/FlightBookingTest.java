import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {

    static WebDriver driver;


    @Test
    public void testThatResultsAppearForAOneWayJourney() throws InterruptedException {

        setDriverPath();
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get("https://www.cleartrip.com/");
        Thread.sleep(2000);
        
        driver.findElement(By.xpath(".//*[@id='Home']/div/div/ul/li/div/div[2]/aside[1]/nav/ul[1]/li[1]/a[1]")).click();
        //driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        Thread.sleep(2000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        Thread.sleep(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();
        Thread.sleep(2000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.quit();

    }


    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void setDriverPath() {
        if (Platform.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (Platform.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "D:\\\\Ashok-Data\\\\Ashok\\\\Data\\\\Testing\\\\chromedriver_win32\\\\chromedriver.exe");
        }
        if (Platform.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
