package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Frame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://toidicodedao.com/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));

        driver.findElement(By.xpath("//a[@title='Tôi đi code dạo' and text()='Tôi đi code dạo']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(), "389,425 followers");

        //quay lại trang chúa iframe
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("div#content-sidebar input.search-field")).sendKeys("puppeteer");
        driver.findElement(By.cssSelector("div#content-sidebar input.search-field")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    @Test
    public void TC_02() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("div#imageTemplateContainer img")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#tooltip")).getText(), "Interactive form loaded. Try filling out below.");

        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("East Dorm");
        driver.findElement(By.xpath("//label[text()='Female']")).click();

        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("a[title='Get this form']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
