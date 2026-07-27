package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_21_Wait_PV_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01() {
        // Cho cho den khi thoa man dieu kien la alert duoc present
        explicitWait.until(ExpectedConditions.alertIsPresent());

        // Element visible (cho 1 hay nhieu, tham so la gi)
        WebElement inputTextbox = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        inputTextbox.sendKeys("");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElement(By.cssSelector("input#email")),
                driver.findElement(By.cssSelector("input#password")),
                driver.findElement(By.cssSelector("input#name"))
        ));

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElements(By.cssSelector("input[tupe='name']"))
        ));

        // Element invisible
        explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(""))));

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));

        explicitWait.until(ExpectedConditions.invisibilityOf(
                driver.findElement(By.cssSelector("input#email"))
        ));

        // Element Presence
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        // Element staleness
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        // Element clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(""))));

        // Element selected
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // Element co so luong bang bao nhieu (it / bang / nhieu)
       explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 5));
       explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 5));
       explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 5));

        // Ket hop 2 dieu kien (AND / OR / NOT)
        // ca 2 deu dung
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))
        ));

        // 1 trong 2
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))
        ));

        // phu dinh dieu kien
        explicitWait.until(ExpectedConditions.not(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        // URL / Title / Text
        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlMatches(""));

        explicitWait.until(ExpectedConditions.titleIs(""));
        explicitWait.until(ExpectedConditions.titleContains(""));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), ""));
        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("")), ""));
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(""), ""));
        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("")), ""));

        // Attribute / DOM property / Frame
        explicitWait.until(ExpectedConditions.attributeToBe(driver.findElement(By.cssSelector("")), "class", "email"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "id", "finish"));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
