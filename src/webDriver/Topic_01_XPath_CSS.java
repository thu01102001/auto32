package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_01_XPath_CSS {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_HTML() {
        driver.get("https://www.w3schools.com/");
        driver.findElement(By.xpath("//div[@class='tnb-right-section']//a[@aria-label='Get Your Own Website With W3Schools Spaces']")).click();

    }

    @Test
    public void TC_02_Locator() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        //id
        driver.findElement(By.id("FirstName"));

        //class
        driver.findElement(By.className("register-next-step-button"));

        //name
        driver.findElement(By.name("FirstName"));

        //tagName
        driver.findElements(By.tagName("input"));

        //linkText
        driver.findElement(By.linkText("Computers"));

        //partialText
        driver.findElement(By.partialLinkText("Recently viewed"));

        //css
        driver.findElement(By.cssSelector("input#FirstName"));

        //XPath
        driver.findElement(By.xpath("//input[@id='FirstName]"));

    }

    @Test
    public void TC_03_Register_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_04_Register_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("123@456@789");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("123@456@789");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("Nguyenthilethu@2001");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("Nguyenthilethu@2001");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0364697188");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
    }

    @Test
    public void TC_05_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("lethuu@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("Nguyenthilethu@2001");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("Nguyenthilethu@2001");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0364697188");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_06_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0364697188");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_07_Incorrect_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("Nguyenthilethu@2001");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("Nguyenthilethu@2001vvv");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0364697188");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_08_Invalid_Phone_01() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("Nguyenthilethu@2001");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("Nguyenthilethu@2001vvv");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("036469718844455555555");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số");
    }

    @Test
    public void TC_08_Invalid_Phone_02() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("Nguyenthilethu@2001");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("Nguyenthilethu@2001vvv");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("1234567890");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
