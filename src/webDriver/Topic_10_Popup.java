package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_In_HTML() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");

        By popup = By.cssSelector("div.pop-container");

        //Nếu popup có hiển thị thì close => click FAQ Link
        //Nếu ko hiển thị thì click vào FAQ link

        if(driver.findElement(popup).isDisplayed()) {
            System.out.println("========= Popup hiển thị =======");
            driver.findElement(By.cssSelector("span.close_icon")).click();
            Thread.sleep(2000);
        }

        System.out.println("========= Popup không hiển thị =======");

        //ko còn hiển thị trươc khi click vào FAQ
        Assert.assertFalse(driver.findElement(popup).isDisplayed());

        driver.findElement(By.xpath("//a[text()='FAQ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.faq_main")).getText(), "FAQ");
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='KMPlayer FAQ']")).isDisplayed());

    }

    @Test
    public void TC_02_Not_in_HTML() throws InterruptedException {
        driver.get("https://tienganhcomaiphuong.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        By popup = By.cssSelector("div.custom-dialog-paper");

        Assert.assertTrue(driver.findElement(popup).isDisplayed());

        driver.findElement(By.cssSelector("input[placeholder='Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input[placeholder='Mật khẩu']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[@class='auth-form']//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        driver.findElement(By.cssSelector("h2 > button.close-btn")).click();
        Thread.sleep(2000);

        //Khi xử lý các element không tồn tại trong html thì nên set lại timeout của implicit về 1 con số ngắn hơn
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertEquals(driver.findElements(popup).size(), 0);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_03_KyNangEnglish() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        Assert.assertTrue(driver.findElement(By.cssSelector("div.modal-content div.right")).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("input#user-password");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_04_Tiki() {
        driver.get("https://tiki.vn/");

        By popup = By.cssSelector("div#VIP_BUNDLE");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
