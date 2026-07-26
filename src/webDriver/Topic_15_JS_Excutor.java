package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_15_JS_Excutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSecond(2);

        Assert.assertEquals(executeForBrowser("return document.domain"), "live.techpanda.org");
        Assert.assertEquals(executeForBrowser("return document.URL"), "https://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(2);

        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/button");
        sleepInSecond(5);

        String samSungMessage = getInnerText();
        Assert.assertTrue(samSungMessage.contains("Samsung Galaxy was added to your shopping cart."));

        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSecond(2);

        Assert.assertEquals(executeForBrowser("return document.title"), "Customer Service");

        hightlightElement("//input[@id='newsletter']");
        scrollToElementOnTop("//input[@id='newsletter']");
        sleepInSecond(2);

        sendkeyToElementByJS("//input[@id='newsletter']", "thu" + new Random().nextInt(999) + "@hotmail.com");
        sleepInSecond(2);

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        sleepInSecond(2);

        driver.switchTo().alert().accept();
        sleepInSecond(3);
        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
        sleepInSecond(2);

        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSecond(10);
        Assert.assertEquals(executeForBrowser("return document.domain"), "www.facebook.com");
    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/html5/index.html");

        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");

        driver.findElement(By.cssSelector("input#fname")).sendKeys("thu");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");

        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");

        driver.findElement(By.cssSelector("input#em")).sendKeys("123!@#$");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");

        driver.findElement(By.cssSelector("input#em")).clear();
        driver.findElement(By.cssSelector("input#em")).sendKeys("123!456");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");
    }

    @Test
    public void TC_03() {
        driver.get("https://login.ubuntu.com/");

        driver.findElement(By.xpath("//span[text()='Log in']/parent::button")).click();
        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please fill out this field.");

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("abc");
        driver.findElement(By.xpath("//span[text()='Log in']/parent::button")).click();
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please include an '@' in the email address. 'abc' is missing an '@'.");

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).clear();
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("thu@gmail.com");
        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_password']"), "Please fill out this field.");

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_password']")).sendKeys("123");
        driver.findElement(By.xpath("//span[text()='Log in']/parent::button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("p.p-notification__response")).getText(), "There were some problems with the information you gave us. Please check below and try again.");
    }

    @Test
    public void TC_04() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSecond(3);

        hightlightElement("//span[text()='Account']/parent::a");
        clickToElementByJS("//span[text()='Account']/parent::a");
        sleepInSecond(2);

        hightlightElement("//div[@id='header-account']//a[text()='My Account']");
        clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
        sleepInSecond(2);

        hightlightElement("//a[@title='Create an Account']");
        clickToElementByJS("//a[@title='Create an Account']");
        sleepInSecond(2);

        sendkeyToElementByJS("//input[@id='firstname']", "nguyen");
        sleepInSecond(2);
        sendkeyToElementByJS("//input[@id='lastname']", "thu");
        sleepInSecond(2);
        sendkeyToElementByJS("//input[@id='email_address']", "thu" + new Random().nextInt(999) + "@gmail.com");
        sleepInSecond(2);
        sendkeyToElementByJS("//input[@id='password']", "123456");
        sleepInSecond(2);
        sendkeyToElementByJS("//input[@id='confirmation']", "123456");
        sleepInSecond(2);

        hightlightElement("//button[@title='Register']");
        clickToElementByJS("//button[@title='Register']");
        sleepInSecond(3);

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));

        hightlightElement("//span[text()='Account']/parent::a");
        clickToElementByJS("//span[text()='Account']/parent::a");
        sleepInSecond(3);

        hightlightElement("//div[@id='header-account']//a[text()='Log Out']");
        clickToElementByJS("//div[@id='header-account']//a[text()='Log Out']");
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Mobile']")).isDisplayed());
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
