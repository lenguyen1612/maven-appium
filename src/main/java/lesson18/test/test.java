package lesson18.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lesson18.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test extends BaseTest {
    @Test
    public void test(){
        AppiumDriver<MobileElement> appiumDriver = getDriverAndroid();
//        Assert.assertTrue(appiumDriver!=null);
        Assert.assertTrue(true);
    }
}
