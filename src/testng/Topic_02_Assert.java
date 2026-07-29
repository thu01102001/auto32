package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
    WebDriver driver;

    @Test
    public void test() {
        // Equals : Kiem tra 2 du lieu co bang nhau khong
        String fullName = "Automation FC";
        Assert.assertEquals(fullName, "Automation FC", "Actual fullname is not the same!");

        // True - False
        // Dieu kien nhan vao la Boolean (isDisplayed / isEnabled / isSelected / isMultiple,...)

        // Mong doi ket qua tra ve dung
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")));
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")), "");
    }

    private boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed()
    }


}
