package lesson18.core;


import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lesson18.drivers.DriverFactory;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {
//lap 18 ko 9 xác như thầy dạy
//    public AppiumDriver<MobileElement> appiumDriver;
//    private DriverFactory driverFactory = new DriverFactory();
//
//    @BeforeClass(alwaysRun = true)
//    public void setup(){
//        driverFactory.startAppiumServer();
//        if(this.appiumDriver==null){
//            this.appiumDriver = driverFactory.getAndroidDriver();
//        }
//
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void afterClass(){
//       driverFactory.stopAppiumServer();
//    }
//lap 19

    private AppiumDriver<MobileElement> appiumDriver;

    private final List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private ThreadLocal<DriverFactoryEx> driverFactoryThreadLocal;
    String uuid, deviceName, systemPort;

    @BeforeTest(alwaysRun = true)
    @Parameters({"uuid","deviceName", "systemPort"})
    public void setup(String uuid, String deviceName, String systemPort){
        this.uuid =uuid;
        this.deviceName = deviceName;
        this.systemPort = systemPort;
        driverFactoryThreadLocal = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverFactoryEx = new DriverFactoryEx();
            driverThreadPool.add(driverFactoryEx);
            return driverFactoryEx;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        driverFactoryThreadLocal.get().quitAppiumSession();
    }

    @AfterMethod
    public void afterMethod(){

    }

    public AppiumDriver<MobileElement> getDriverAndroid(){
        System.out.println("uuid: " + uuid + " deviceName: " + deviceName + " systemPort: " + systemPort);
        if(appiumDriver==null){
            appiumDriver = driverFactoryThreadLocal.get().getAndroidDriver(uuid, deviceName, systemPort);
        }
        return appiumDriver;
    }
}
