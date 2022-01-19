package driver;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactoryEx {
    private AppiumDriver<MobileElement> appiumDriver;

    public AppiumDriver<MobileElement> getAndroidDriver(String udid, String deviceName, String systemPort) {
        initDriver(udid, deviceName, systemPort);
        return appiumDriver;
    }

    public AppiumDriver<MobileElement> getAndroidDriver() {
        return appiumDriver;
    }

    private void initDriver(String uuid, String deviceName, String systemPort) {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, uuid);
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, systemPort);
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

            URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quitAppiumSession() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
