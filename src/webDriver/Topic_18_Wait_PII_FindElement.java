package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic_18_Wait_PII_FindElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_FindElement() {
        driver.get("https://demo.opencart.com/en-gb?route=account/login");

        // 1 - Nếu như tìm element có duy nhất 1 cái
        // Ko can cho het total time la 15s
        // Chuyen qua action tiep theo luon
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#input-email"));
        System.out.println("End time: " + getDateTimeNow());

        // 2 - Nếu như tìm element có > 1 cái => Thao tac voi thang dau tien
        // Ko can cho het total time la 15s
        // No luon lay element dau tien de thao tac
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("thu");
        System.out.println("End time: " + getDateTimeNow());

        // 3 - Nếu như ko tìm thay element
        // Co gang tim di tim lai cu moi 0.5s tim 1 lan
        // Neu giua chung tim thay thi ko can cho het total time con lai
        // Neu het time ko tim thay thi se show ra Exception: NoSuchElement va danh fail testcase tai vi tri day
        // Ko chay cac step con lai nua
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input[type='emaill']"));
        System.out.println("End time: " + getDateTimeNow());
    }

    @Test
    public void TC_02_FindElements() {
        driver.get("https://demo.opencart.com/en-gb?route=account/login");
        List<WebElement> elementList = null;

        // 1 - Nếu như tìm element có duy nhất 1 cái
        // Ko can cho het total time la 15s
        // Chuyen qua action tiep theo luon
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElements(By.cssSelector("input#input-email"));
        System.out.println("Tong so luong element trong list: " +elementList.size());
        System.out.println("End time: " + getDateTimeNow());

        // 2 - Nếu như tìm element có > 1 cái
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElements(By.cssSelector("input[type=['email']"));
        System.out.println("Tong so luong element trong list: " +elementList.size());
        System.out.println("End time: " + getDateTimeNow());

        // 3 - Nếu như ko tìm thay element
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElements(By.cssSelector("input[type=['emailll']"));
        System.out.println("Tong so luong element trong list: " +elementList.size());
        System.out.println("End time: " + getDateTimeNow());

        // Cho het timeout
        // Het timeout ko tim thay thi ko danh fail testcase sau do se tra ve list rong = 0

    }

    private String getDateTimeNow() {
        return new Date().toString();
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
