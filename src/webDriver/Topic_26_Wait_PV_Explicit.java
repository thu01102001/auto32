package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Wait_PV_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    String uploadFilePath = System.getProperty("user.dir") + "\\uploadFiles\\";
    String flower1 = "anh1.jpg";
    String flower2 = "anh2.jpg";
    String flower3 = "anh3.jpg";

    String flower1FilePath = uploadFilePath + flower1;
    String flower2FilePath = uploadFilePath + flower2;
    String flower3FilePath = uploadFilePath + flower3;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start button"))).click();

        //invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));

        //visible
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4"))).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Ajax() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // Chờ trong vòng 30s để cho Date Picker hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.contentWrapper")));

        // Wait cho text được xuất hiện trong vòng 30s
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),
                "No Selected Dates to display.")));

        // Wait cho element được phép click và sau đó click vào
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='18']"))).click();

        // Wait cho icon loading biết mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body[@class='qsf-body demo-page']/div[1]/div[1]"))));

        // Wait cho text được cập nhật lên trang
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),
                "Saturday, July 18, 2026")));
    }

    @Test
    public void TC_03_GoFile() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://gofile.io");

        // CHờ cho tất cả các loading icon ở trên trang hiện tại biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.animate-spin")))));

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")))
                .sendKeys(flower1FilePath + "\n" + flower2FilePath + "\n" + flower3FilePath);

        // CHờ cho tất cả các Upload Progress biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.file-progressbar")))));

        String uploadUrl = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.linkSuccessCard"))).getDomAttribute("href");
        driver.get(uploadUrl);

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.animate-spin")))));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'item_open') and text()='" + flower1 + "']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'item_open') and text()='" + flower2 + "']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'item_open') and text()='" + flower3 + "']")));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
