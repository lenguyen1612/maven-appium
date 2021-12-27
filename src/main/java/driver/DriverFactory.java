package driver;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverFactory {

    private static AppiumDriverLocalService appiumServer;
    private static AndroidDriver<MobileElement> androidDriver;

    public static void startAppiumServer(){
        try{
            AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
//        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
            appiumServiceBuilder.withIPAddress("127.0.0.1").usingPort(4723);

            appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
            appiumServer.start();
        }catch (SessionNotCreatedException e){
            stopAppiumServer();
        }


    }

    public static void stopAppiumServer(){
//        appiumServer.stop();
        String killNodeWinCmd = "taskkill /F /IM node.exe";
        String killNodeLinuxCmd = "killall node";

        String killNodeCmd = System.getProperty("os.name").toLowerCase().startsWith("windows") ? killNodeWinCmd : killNodeLinuxCmd;

        Runtime runtime = Runtime.getRuntime();
        try{
            runtime.exec(killNodeCmd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static AndroidDriver<MobileElement> getAndroidDriver(){
        initAndroidDriver();
        return androidDriver;
    }

    private static void initAndroidDriver(){
        try {
            // Setup DesiredCapabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, "android_2901");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability("newCommandTimeout", 120);

            // {capabilities} ----> Appium Server:4723
//            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            androidDriver = new AndroidDriver<MobileElement>(appiumServer.getUrl(), desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
