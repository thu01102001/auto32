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
import java.util.Random;

public class Topic_05_Textbox_TextArea {
    WebDriver driver;
    WebDriverWait explicitWait;
    String firstName = "Nguyễn", lastName = "Thu", pass = "Nguyenthu@2001", username = "lethu", number = "0364697189", comment = "Nguyen thi le thu";
    String emailAddress = "thu" + new Random().nextInt(999) + "@gmail.com";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Techpanda() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(pass);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(pass);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");

        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(firstName + " " +lastName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(emailAddress));

        driver.findElement(By.xpath("//a[text()='Mobile']/parent::li")).click();

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2")).click();

        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Best");
        Assert.assertEquals(driver.findElement(By.cssSelector("input#nickname_field")).getAttribute("value"), firstName);

        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");
    }

    @Test
    public void TC_02_OrangeHRM() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.findElement(By.xpath("//span[text()='PIM']/parent::a/parent::li")).click();

        driver.findElement(By.xpath("//a[text()='Add Employee']/parent::li")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner")));

        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");

        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(pass);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner")));

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner")));

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner")));

        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner")));

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);

        driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
        driver.findElement(By.xpath("//a[text()='Logout']/parent::li")).click();

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.findElement(By.xpath("//span[text()='My Info']/parent::a/parent::li")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner")));
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner")));

        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner")));

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
