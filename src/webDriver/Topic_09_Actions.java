package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_09_Actions {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).pause(Duration.ofSeconds(5)).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover() {
        driver.get("http://www.myntra.com/");

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']"))).pause(Duration.ofSeconds(3)).perform();
        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Hover() {
        driver.get("https://www.fahasa.com/");

        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).pause(Duration.ofSeconds(5)).perform();

        List<WebElement> allSubMenu = driver.findElements(By.cssSelector("li.aligned-left span.menu-title"));
        for (WebElement subMenu : allSubMenu) {
            System.out.println(subMenu.getText());
        }

        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ Chơi']//ancestor::li"))).pause(Duration.ofSeconds(4)).perform();

        actions.click(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='My Little Pony']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "MY LITTLE PONY");
    }

    @Test
    public void TC_04_Click_And_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> number = driver.findElements(By.cssSelector("ol#selectable li"));
        actions.clickAndHold(number.get(0)).moveToElement(number.get(3)).release().perform();

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(numberSelected.size(), 4);
    }

    @Test
    public void TC_05_Select_And_Click() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> number = driver.findElements(By.cssSelector("ol#selectable li"));

        actions.keyDown(Keys.CONTROL).perform();
        actions.click(number.get(0))
                .click(number.get(2))
                .click(number.get(5))
                .click(number.get(10)).perform();
        actions.keyUp(Keys.CONTROL).perform();

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(numberSelected.size(), 4);
    }

    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_07_Right_Click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list.context-menu-root")).isDisplayed());

        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']//parent::li"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        actions.click(driver.findElement(By.xpath("//span[text()='Quit']//parent::li"))).pause(Duration.ofSeconds(3)).perform();
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
