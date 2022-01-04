package tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestTestNG {

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @Test
    public void test(){
        Assert.assertEquals(false, false);
        System.out.println("test");
    }

    @Test
    public void test1(){
        Assert.assertTrue(true);
        System.out.println("test1");
    }

    @Test
    public void test2(){
        Assert.assertFalse(false);
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
        SoftAssert softAssert = new SoftAssert();
        System.out.println("*** test case two started ***");
        softAssert.assertEquals("Hello", "Hello", "First soft assert passed");
        System.out.println("hard assert success....");
        softAssert.assertTrue("Hello".equals("hello"), "Second soft assert failed");
        softAssert.assertTrue("Welcome".equals("welcomeeee"), "Third soft assert failed");
        System.out.println("*** test case two executed successfully ***");
        //softAssert.assertAll();
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest");
    }

}
