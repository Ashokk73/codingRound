import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();
        driver = new ChromeDriver();
    	driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
        WebElement ele = driver.findElement(By.xpath(".//*[@id='Home']/div/div/ul/li/div/div[2]/aside[1]/nav/ul[1]/li[2]/a[1]"));
        ele.click();
        WebElement localityTextBox = driver.findElement(By.xpath(".//*[@id='Tags']"));
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        Select travellers = new Select(driver.findElement(By.xpath(".//*[@id='travellersOnhome']")));
        travellers.selectByIndex(2);

        driver.quit();

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "D:\\Ashok-Data\\Ashok\\Data\\Testing\\chromedriver_win32\\chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
