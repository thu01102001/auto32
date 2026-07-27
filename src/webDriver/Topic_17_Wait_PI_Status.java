package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Wait_PI_Status {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Visible() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_02_Invisible_In_HTML() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("thu@gmail.com");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_02_Invisible_Not_In_HTML() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("thu@gmail.com");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_03_Presence() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        //dk1
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        driver.findElement(By.cssSelector("input#email")).sendKeys("thu@gmail.com");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        //dk2
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_04_Staleness() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        WebElement emailErrorMessage = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        driver.navigate().refresh();

        explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
