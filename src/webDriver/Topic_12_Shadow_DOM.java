package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_12_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        WebElement firstShadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext firstShadowRoot = firstShadowHost.getShadowRoot();
        Assert.assertTrue(firstShadowRoot.findElement(By.cssSelector("span#shadow_content span")).isDisplayed());

        WebElement secondShadowHost = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadowRoot = secondShadowHost.getShadowRoot();
        Assert.assertTrue(secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content div")).isDisplayed());
        Assert.assertFalse(firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']")).isSelected());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
