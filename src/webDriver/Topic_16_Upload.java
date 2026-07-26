package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Upload {
    WebDriver driver;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() throws InterruptedException {
        //File de o dau
        //file co dinh tren may => Qua may ng khac ko tim thay duong dan
        //=> Bat ky may nao cung chay duoc
        //=> De file trong chinh bo source code
        //=> Lay duong dan tuong doi cua file ra
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //Load file
        By uploadFileBy = By.cssSelector("input[type='file']");
        driver.findElement(uploadFileBy).sendKeys(flower1FilePath + "\n" + flower2FilePath + "\n" + flower3FilePath);

        Thread.sleep(4000);

        //File duoc load thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + flower1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + flower2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + flower3 + "']")).isDisplayed());

        List<WebElement> startUploadBtns = driver.findElements(By.cssSelector("table.table-striped button.start"));
        for (WebElement startUploadBtn : startUploadBtns) {
            startUploadBtn.click();
            Thread.sleep(1000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + flower1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + flower2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + flower3 + "']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
