package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_06_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        driver.get("https://rode.com/en-au/support/where-to-buy");

        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());

        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");

        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        Assert.assertEquals(driver.findElements(By.cssSelector("h4.text-left")).size(), 16);

        List<WebElement> dealers = driver.findElements(By.cssSelector("h4.text-left"));

        for(WebElement dealer : dealers) {
            System.out.println(dealer.getText());
        }
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        driver.findElement(By.cssSelector("span#speed-button")).click();
        List<WebElement> options = driver.findElements(By.cssSelector("div.ui-menu-item-wrapper"));
        for(WebElement option : options) {
            if(option.getText().equals("Medium")) {
                option.click();
            }
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Medium");

        Thread.sleep(3000);

        driver.findElement(By.cssSelector("span#speed-button")).click();
        options = driver.findElements(By.cssSelector("div.ui-menu-item-wrapper"));
        for(WebElement option : options) {
            if(option.getText().equals("Slower")) {
                option.click();
            }
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Slower");

        Thread.sleep(3000);

        driver.findElement(By.cssSelector("span#speed-button")).click();
        options = driver.findElements(By.cssSelector("div.ui-menu-item-wrapper"));
        for(WebElement option : options) {
            if(option.getText().equals("Faster")) {
                option.click();
            }
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Faster");
    }

    @Test
    public void TC_03() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        driver.findElement(By.cssSelector("div.selection.dropdown")).click();
        List<WebElement> options = driver.findElements(By.cssSelector("div.transition span"));
        for(WebElement option : options) {
            if (option.getText().equals("Jenny Hess")) {
                option.click();
            }
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
    }

    @Test
    public void TC_04() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        driver.findElement(By.cssSelector("li.dropdown-toggle")).click();
        List<WebElement> options = driver.findElements(By.cssSelector("ul.dropdown-menu a"));
        for (WebElement option : options) {
            if(option.getText().equals("Second Option")) {
                option.click();
            }
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
