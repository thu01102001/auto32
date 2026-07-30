package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Priority {
    WebDriver driver;

    @BeforeClass
    public void initBrowserDriver() {
    }

    @Test(priority = 1, description = "Create new account")
    public void createNewAccount(){
        System.out.println("createNewAccount");
    }

    @Test(priority = 2)
    public void editAccount(){
        System.out.println("editAccount");
    }

    @Test(priority = 3, enabled = false)
    public void moveAccount(){
        System.out.println("moveAccount");
    }

    //@Test(priority = 4)
    public void deleteAccount(){
        System.out.println("deleteAccount");
    }


    @AfterClass
    public void cleanBrowserDriver() {
    }

}
