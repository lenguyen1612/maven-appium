package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchApp {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = null;

        try {
            // Setup DesiredCapabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("deviceName", "android_2901");
            desiredCapabilities.setCapability("udid", "emulator-5554");
            desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
            desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability("newCommandTimeout", 120);

            // {capabilities} ----> Appium Server:4723
            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
        } catch (Exception ignored) {}

        // Interact with Mobile Element
        if (appiumDriver != null) {
            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

            System.out.println("Connected to appium server and launched target app!");
            MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

        } else {
            System.out.println("Error when connecting with Appium server!");
        }
    }
}
