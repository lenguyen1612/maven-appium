package lesson18.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lesson18.drivers.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public AppiumDriver<MobileElement> appiumDriver;
    private DriverFactory driverFactory = new DriverFactory();

    @BeforeClass(alwaysRun = true)
    public void setup(){
        driverFactory.startAppiumServer();
        if(this.appiumDriver==null){
            this.appiumDriver = driverFactory.getAndroidDriver();
        }

    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
       driverFactory.stopAppiumServer();
    }

}
