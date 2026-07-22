package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_WebElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Display() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email is displayed");
        }

        if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education is displayed");
        }

        if(driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Age under 18 is displayed");
        }

        if(!driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("User 5 is not displayed");
        }
    }

    @Test
    public void TC_02_Enable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            System.out.println("Email is enabled");
        } else {
            System.out.println("Email is disabled");
        }

        if(driver.findElement(By.cssSelector("textarea#edu")).isEnabled()) {
            System.out.println("Education is enabled");
        } else {
            System.out.println("Education is disabled");
        }

        if(driver.findElement(By.cssSelector("input#under_18")).isEnabled()) {
            System.out.println("Age under 18 is enabled");
        } else {
            System.out.println("Age under 18 is disabled");
        }

        if(driver.findElement(By.cssSelector("select#job1")).isEnabled()) {
            System.out.println("Job 1 is enabled");
        } else {
            System.out.println("Job 1 is disabled");
        }

        if(driver.findElement(By.cssSelector("select#job2")).isEnabled()) {
            System.out.println("Job 2 is enabled");
        } else {
            System.out.println("Job 2 is disabled");
        }

        if(driver.findElement(By.cssSelector("input#development")).isEnabled()) {
            System.out.println("Checkbox Development is enabled");
        } else {
            System.out.println("Checkbox Development is disabled");
        }

        if(driver.findElement(By.cssSelector("input#slider-1")).isEnabled()) {
            System.out.println("Slide 1 is enabled");
        } else {
            System.out.println("Slider 1 is disabled");
        }

        if(driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
            System.out.println("Password is enabled");
        } else {
            System.out.println("Password is disabled");
        }

        if(driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled()) {
            System.out.println("Radio button is enabled");
        } else {
            System.out.println("Radio button is disabled");
        }

        if(driver.findElement(By.cssSelector("textarea#bio")).isEnabled()) {
            System.out.println("Bio is enabled");
        } else {
            System.out.println("Bio is disabled");
        }

        if(driver.findElement(By.cssSelector("select#job3")).isEnabled()) {
            System.out.println("Job 3 is enabled");
        } else {
            System.out.println("Job 3 is disabled");
        }

        if(driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()) {
            System.out.println("Checkbox is enabled");
        } else {
            System.out.println("Checkbox is disabled");
        }

        if(driver.findElement(By.cssSelector("input#slider-2")).isEnabled()) {
            System.out.println("Slide 2 is enabled");
        } else {
            System.out.println("Slider 2 is disabled");
        }
    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(!driver.findElement(By.cssSelector("input#under_18")).isSelected()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Age under 18 is selected");
        } else {
            System.out.println("Age under 18 is de-selected");
        }

        if(!driver.findElement(By.cssSelector("input#java")).isSelected()) {
            driver.findElement(By.cssSelector("input#java")).click();
            System.out.println("Java checkbox is selected");
        } else {
            System.out.println("Java checkbox is de-selected");
        }

        if(driver.findElement(By.cssSelector("input#java")).isSelected()) {
            driver.findElement(By.cssSelector("input#java")).click();
            System.out.println("Java checkbox is de-selected");
        } else {
            System.out.println("Java checkbox is selected");
        }
    }

    @Test
    public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");

        if(driver.findElement(By.cssSelector("input#email")).isEnabled()) {
            driver.findElement(By.cssSelector("input#email")).sendKeys("nguyenthilethu2001vn@gmail.com");
            System.out.println("Email is enabled");
        } else {
            System.out.println("Email is disabled");
        }

        //nhập số
        if(driver.findElement(By.cssSelector("input#new_password")).isEnabled()) {
            System.out.println("Password is enabled");
            driver.findElement(By.cssSelector("input#new_password")).sendKeys("122");

            Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        } else {
            System.out.println("Password is disabled");
        }

        driver.findElement(By.cssSelector("input#new_password")).clear();

        //Nhập chữ thường
        if(driver.findElement(By.cssSelector("input#new_password")).isEnabled()) {
            System.out.println("Password is enabled");
            driver.findElement(By.cssSelector("input#new_password")).sendKeys("abc");

            Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        } else {
            System.out.println("Password is disabled");
        }

        driver.findElement(By.cssSelector("input#new_password")).clear();

        //Nhập chữ hoa
        if(driver.findElement(By.cssSelector("input#new_password")).isEnabled()) {
            System.out.println("Password is enabled");
            driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABC");

            Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        } else {
            System.out.println("Password is disabled");
        }

        driver.findElement(By.cssSelector("input#new_password")).clear();

        //Nhập > 8 ký tự
        if(driver.findElement(By.cssSelector("input#new_password")).isEnabled()) {
            System.out.println("Password is enabled");
            driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456789");

            Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        } else {
            System.out.println("Password is disabled");
        }

        driver.findElement(By.cssSelector("input#new_password")).clear();

        // Nhập đúng
        if(driver.findElement(By.cssSelector("input#new_password")).isEnabled()) {
            System.out.println("Password is enabled");
            driver.findElement(By.cssSelector("input#new_password")).sendKeys("Nguyenthilethu@2001");

            Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
            Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
            Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
            Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
            Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
            Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        } else {
            System.out.println("Password is disabled");
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
