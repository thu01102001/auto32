package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_28_Wait_PVII_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;
    FluentWait<WebElement> elementFluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
    }

//    @Test
//    public void TC_01() {
//        // Mặc định thời gian tìm lại element (polling / interval time): 500ms = 0.5s
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//
//        // Mặc định thời gian tìm lại element (polling / interval time): 500ms = 0.5s
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//        //Custom thời gian tìm lại element (polling / interval time)
//        // 100ms tìm lại 1 lần = 1 giây tìm lại được 10 lần
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(100));
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofSeconds(1));
//
//        fluentWait = new FluentWait(driver);
//        fluentWait.withTimeout(Duration.ofSeconds(15))
//                .pollingEvery(Duration.ofMillis(100))
//                .ignoring(NoSuchElementException.class);
//
//        fluentWait.until(new Function<WebDriver, String>() {
//            @Override
//            public String apply(WebDriver driver) {
//                return "";
//            }
//        });
//
//        // Yêu cầu là gì
//        // Ra được điều kiện cần lấy
//    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        clickToElement("div#finish h4");

//        fluentWait.until(new Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(By.cssSelector("div#finish h4"));
//            }
//        }).click();

//        driver.findElement(By.cssSelector("div#start button")).click();

        isElemetnDisplay("div#finish h4");

//        Assert.assertTrue(fluentWait.until(new Function<WebDriver, Boolean>() {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                return driver.findElement(By.cssSelector("div#finish h4")).isDisplayed();
//            }
//        }));

        String helloWorldText = getElementText("div#finish h4");
//        String helloWorldText = fluentWait.until(new Function<WebDriver, String>() {
//            @Override
//            public String apply(WebDriver driver) {
//                return driver.findElement(By.cssSelector("div#finish h4")).getText();
//            }
//        });

        Assert.assertEquals(helloWorldText, "Hello World!");
    }

    @Test
    public void TC_03() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countdownElement = getElement("div#javascript_countdown_time");

        elementFluentWait = new FluentWait<>(countdownElement);

        elementFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        Assert.assertTrue(elementFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                String text = element.getText();
                System.out.println(text);
                return element.getText().endsWith("00");
            }
        }));
    }

    // Viết hàm đẻ sửa lại các hàm findElement, click, getText, isDisplay với polling time mới
    //FindElement
    private WebElement getElement(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(cssLocator));
            }
        });
    }

    //Click
    private void clickToElement(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(cssLocator));
            }
        }).click();
    }

    //GetText
    private String getElementText(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(cssLocator)).getText();
            }
        });
    }

    //Display
    private boolean isElemetnDisplay(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
            }
        });
    }

    private Boolean waitVisible(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        return fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
                    }
                });
        
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
