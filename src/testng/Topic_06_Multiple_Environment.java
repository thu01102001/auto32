package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_06_Multiple_Environment {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String serverUrl;

    @Parameters({"serverName", "browserName"})
    @BeforeClass
    public void beforeClass(@Optional("LIVE") String serverName, String browserName) {
//        if (browserName.equals("Chrome")) {
//            driver = new ChromeDriver();
//        } else if (browserName.equals("Firefox")) {
//            driver = new FirefoxDriver();
//        } else if (browserName.equals("Edge")) {
//            driver = new EdgeDriver();
//        } else {
//            throw new RuntimeException("Browser name is not valid");
//        }

        switch (browserName) {
            case "DEV":
                serverUrl = "https://www.dev.Fahase.com";
                break;
            case "TEST":
                serverUrl = "https://www.test.Fahase.com";
                break;
            case "LIVE":
                serverUrl = "https://www.Fahase.com";
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }

        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }

        jsExecutor = ((JavascriptExecutor)driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        driver.get(serverUrl + "/customer/account/create");

        driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        System.out.println(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color"));

        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("lethu@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("Lethu@2001");

        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
