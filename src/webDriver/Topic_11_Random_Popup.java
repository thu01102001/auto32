package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Random_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://tiki.vn/");

        By popupRandom = By.cssSelector("div#VIP_BUNDLE");
        if(driver.findElement(popupRandom).isDisplayed()) {
            driver.findElement(By.cssSelector("img[alt='close-icon']")).click();
        }
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElements(popupRandom).size(), 0);

        driver.findElement(By.xpath("//span[text()='Tài khoản']/parent::div")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[2]")).getText(), "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("button.btn-close")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);
    }

    @Test
    public void TC_02_Edu() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");

        By popup = By.cssSelector("div.pum-container");
        Thread.sleep(2000);
        if(driver.findElement(popup).isDisplayed()) {
            driver.findElement(By.cssSelector("button.pum-close")).click();
        }
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(popup).isDisplayed());
        driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content h1")).getText(), "LỊCh Khai GiẢNg Trung TÂM VNK EDU");
    }

    @Test
    public void TC_03_JavaCode() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        Thread.sleep(10000);
        By popup = By.xpath("//div[contains(@class, 'lepopup-popup-container')]/div[1]");

        if(driver.findElements(popup).size() > 0) {
            driver.findElement(By.xpath("//a[text()='×']")).click();
        }
        Thread.sleep(2000);

        //Assert.assertFalse(driver.findElement(popup).isDisplayed());
        Assert.assertEquals(driver.findElements(popup).size(), 0);

        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@id='posts-container']/li[1]//h2/a")).getText(), "Agile Testing Explained");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
