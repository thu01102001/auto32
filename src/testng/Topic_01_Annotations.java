package testng;

import org.testng.annotations.*;

public class Topic_01_Annotations {
    //Chi dan
    @BeforeClass(groups = "test")
    public void method01() {
        System.out.println("BeforeClass");
    }

    @BeforeGroups(groups = "test")
    public void method02() {
        System.out.println("BeforeGroups");
    }

    @BeforeMethod(groups = "test")
    public void method03() {
        System.out.println("BeforeMethod");
    }

    @BeforeSuite(groups = "test")
    public void method04() {
        System.out.println("BeforeSuite");
    }

    @BeforeClass(groups = "test")
    public void method05() {
        System.out.println("BeforeClass");
    }

    @Test(groups = "test")
    public void method06() {
        System.out.println("Test 01");
    }

    @Test(groups = "test")
    public void method07() {
        System.out.println("Test 02");
    }

    @AfterTest(groups = "test")
    public void method08() {
        System.out.println("AfterTest");
    }

    @AfterSuite(groups = "test")
    public void method09() {
        System.out.println("AfterSuite");
    }

    @AfterMethod(groups = "test")
    public void method010() {
        System.out.println("AfterMethod");
    }

    @AfterGroups(groups = "test")
    public void method011() {
        System.out.println("AfterMethod");
    }

    @AfterClass(groups = "test")
    public void method012() {
        System.out.println("AfterClass");
    }

}
