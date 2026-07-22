package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_07_Btn_Checkbox {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = ((JavascriptExecutor)driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        System.out.println(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color"));

        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("lethu@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("Lethu@2001");

        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
    }

    @Test
    public void TC_02_Checkbox_Radio() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='demo-runner']")));
        Thread.sleep(3000);

        if (!driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        if(driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By carEngine = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='demo-runner']")));
        Thread.sleep(3000);

        if (!driver.findElement(carEngine).isSelected()) {
            driver.findElement(carEngine).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(carEngine).isSelected());
    }

    @Test
    public void TC_03_Checkbox_Radio() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");
        if(!driver.findElement(By.cssSelector("input[value='Summer']")).isSelected()) {
            driver.findElement(By.cssSelector("input[value='Summer']")).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Summer']")).isSelected());

        driver.get("https://material.angular.io/components/checkbox/examples");
        if(!driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());

        if(!driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());

        if(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());


        if(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());
    }

    @Test
    public void TC_04_Select_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("div#cid_52 input"));

        for (WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        for (WebElement checkbox : checkboxes) {
            if(checkbox.isSelected()) {
                checkbox.click();
            }
        }

        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        for(WebElement checkbox : checkboxes) {
            if (checkbox.getDomAttribute("value").equals("Fainting Spells")) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }

        for(WebElement checkbox : checkboxes) {
            if (checkbox.getDomAttribute("value").equals("Fainting Spells")) {
                Assert.assertTrue(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_05_ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");

        if (!driver.findElement(By.cssSelector("input#id_new_user")).isSelected()) {
            jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input#id_new_user")));
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

        if(!driver.findElement(By.cssSelector("input#id_accept_tos")).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input#id_accept_tos")));
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_accept_tos")).isSelected());
    }

    @Test
    public void TC_06_Docs() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(2000);

        By canthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");

        driver.findElement(canthoRadio).click();
        Thread.sleep(2000);

        //Verify = các hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());

        //Verify lấy thuộc tính
        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute("aria-checked"), "true");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
