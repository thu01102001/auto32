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
import java.util.Date;

public class Topic_27_Wait_PVI_Mix {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Element_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://localhost/");

        System.out.println("Start time " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#small-searchterms"));
        System.out.println("End time " + getDateTimeNow());

        System.out.println("Start time " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small-searchterms")));
        System.out.println("End time " + getDateTimeNow());
    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://localhost/");

        System.out.println("Start time " + getDateTimeNow());

        try {
            driver.findElement(By.cssSelector("input#small"));
        } catch (Exception e) {
            System.out.println("End time " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_02_Element_Not_Found_Only_Explicit() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://localhost/");

        System.out.println("Start time " + getDateTimeNow());

        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#smail")));
        } catch (Exception e) {
            System.out.println("End time " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_04_Element_Not_Found_Combine() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://localhost/");

        System.out.println("Start time " + getDateTimeNow());

        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#smail")));
        } catch (Exception e) {
            System.out.println("End time " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    private String getDateTimeNow() {
        return new Date().toString();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
