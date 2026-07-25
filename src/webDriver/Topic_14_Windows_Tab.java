package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.Set;

public class Topic_14_Windows_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //lay ra id cua tab hien tai dang dung o do
        String githubWindowID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

        //lay ra id cua tat ca cac tab dang co
        switchToWindowByID(githubWindowID);

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("auto test");
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys(Keys.ENTER);
        sleepInSecond(3);

        String googleWindowID = driver.getWindowHandle();
        switchToWindowByID(googleWindowID);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);

        switchToWindowByTitle("Facebook");
        sleepInSecond(3);
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("thu");
        driver.findElement(By.cssSelector("input[name='pass']")).sendKeys("thu");
        sleepInSecond(3);

        switchToWindowByTitle("Selenium WebDriver");
        System.out.println(driver.getTitle());

        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
        sleepInSecond(3);

        closeAllWindowWithoutParent(githubWindowID);
        sleepInSecond(3);

    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2//following-sibling::div//a[text()='Add to Compare']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div//a[text()='Add to Compare']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();

        switchToWindowByTitle("Products Comparison List");
        sleepInSecond(2);

        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        sleepInSecond(2);

        switchToWindowByTitle("Mobile");
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_03() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");

        if(driver.findElement(By.cssSelector("div#onetrust-banner-sdk")).isDisplayed()) {
            driver.findElement(By.xpath("//button[text()='I Accept']")).click();
            sleepInSecond(3);
            System.out.println("Popup xuat hien");
        } else {
            System.out.println("Popup ko xuat hien");
        }

        driver.findElement(By.xpath("//span[@class='tb' and text()='Đăng nhập']")).click();

        switchToWindowByTitle("Login");
        sleepInSecond(2);
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h2[text()='Log in with your email account']/following-sibling::div/input[@name='username']/following-sibling::span")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//h2[text()='Log in with your email account']/following-sibling::div/input[@name='password']/following-sibling::span")).getText(), "This field is required");

        closeAllWindowWithoutParent("Cambridge Dictionary");
        sleepInSecond(2);
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("hello");
        driver.findElement(By.cssSelector("button[title='Tìm kiếm']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='cald4-1']/following-sibling::div//span[@class='hw dhw']")).getText(), "hello");
    }

    @Test
    public void TC_04() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        String firstWindowID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        sleepInSecond(15);
        switchToWindowByID(firstWindowID);
        sleepInSecond(3);
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.findElement(By.cssSelector("button#harvard-key-button")).isDisplayed());
        sleepInSecond(3);
        String secondWindowID = driver.getWindowHandle();
        switchToWindowByID(secondWindowID);
        sleepInSecond(3);

        if(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed()) {
            Assert.assertTrue(driver.findElement(By.xpath("//p[@class='sam-wait__message']")).isDisplayed());
            driver.findElement(By.xpath("//div[@class='sam-wait__head ']/button")).click();
        }

        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("h");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2026");
        sleepInSecond(2);
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Full Term");
        driver.findElement(By.cssSelector("button#search-button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.panel__info-bar")).isDisplayed());
    }

    private void switchToWindowByID(String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    private void switchToWindowByTitle(String expectedPageTitle) throws InterruptedException {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            sleepInSecond(1);

            if (driver.getTitle().contains(expectedPageTitle)) {
                break;
            }
        }
    }

    //close tat ca window
    private void closeAllWindowWithoutParent(String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }

    private void sleepInSecond(long timeSecond) throws InterruptedException {
        try {
            Thread.sleep(timeSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
