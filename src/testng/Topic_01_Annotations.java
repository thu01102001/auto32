package testng;

import org.testng.annotations.*;

public class Topic_01_Annotations {
    //Chi dan
    @BeforeClass
    public void method01() {
        System.out.println("BeforeClass");
    }

    @BeforeGroups
    public void method02() {
        System.out.println("BeforeGroups");
    }

    @BeforeMethod
    public void method03() {
        System.out.println("BeforeMethod");
    }

    @BeforeSuite
    public void method04() {
        System.out.println("BeforeSuite");
    }

    @BeforeClass
    public void method05() {
        System.out.println("BeforeClass");
    }

    @Test
    public void method06() {
        System.out.println("Test 01");
    }

    @Test
    public void method07() {
        System.out.println("Test 02");
    }

    @AfterTest
    public void method08() {
        System.out.println("AfterTest");
    }
    @AfterSuite
    public void method09() {
        System.out.println("AfterSuite");
    }
    @AfterMethod
    public void method010() {
        System.out.println("AfterMethod");
    }
    @AfterGroups
    public void method011() {
        System.out.println("AfterMethod");
    }
    @AfterClass
    public void method012() {
        System.out.println("AfterClass");
    }

}
