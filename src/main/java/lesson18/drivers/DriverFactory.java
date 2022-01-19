package lesson18.drivers;

import caps.MobileCapabilityTypeEx;
import flags.AndroidServerFlagEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

//    private static AppiumDriverLocalService appiumServer;
    private static AppiumDriver<MobileElement> appiumDriver;

//    public static void startAppiumServer() {
//        try {
//            AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
//            appiumServiceBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE, "chromedriver_autodownload");
//            appiumServiceBuilder.withIPAddress("127.0.0.1").usingPort(4723);
//
//            appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
//            appiumServer.start();
//        } catch (SessionNotCreatedException e) {
//            stopAppiumServer();
//        }
//    }

    public static void stopAppiumServer() {
        String killNodeWinCmd = "taskkill /F /IM node.exe";
        String killNodeLinuxCmd = "killall node";

        String killNodeCmd = System.getProperty("os.name").toLowerCase().startsWith("windows") ? killNodeWinCmd : killNodeLinuxCmd;

        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AppiumDriver<MobileElement> getAndroidDriver(String uuid, String deviceName, String systemPort) {
        initAndroidDriver(uuid, deviceName, systemPort);
        return appiumDriver;
    }

    private static void initAndroidDriver(String uuid, String deviceName, String systemPort) {
        try {
            // Setup DesiredCapabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, uuid);
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, systemPort);
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability("newCommandTimeout", 120);

            // {capabilities} ----> Appium Server:4723
            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
