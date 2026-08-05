package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_05_Data_Provider {
    WebDriver driver;
    WebDriverWait explicitWait;
//    String firstName = "Nguyễn", lastName = "Thu", pass = "Nguyenthu@2001", username = "lethu", number = "0364697189", comment = "Nguyen thi le thu";
//    String emailAddress = "thu" + new Random().nextInt(999) + "@gmail.com";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @DataProvider(name = "employee")
    public Object[][] employeeData() {
        return new Object[][] {
                {"John1", "Terryy1", "lethuu1", "Lethu@2001"},
                {"John2", "Terryy2", "lethuu2", "Lethu@2002"},
                {"John3", "Terryy3", "lethuu3", "Lethu@2003"}
        };
    }

    @Test(dataProvider = "employee")
    public void TC_02_OrangeHRM(String firstName, String lastName, String username, String pass) {
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

        driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
        driver.findElement(By.xpath("//a[text()='Logout']/parent::li")).click();

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
