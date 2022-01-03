package tests;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HandleMultipleApps {


    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        androidDriver.manage().timeouts().implicitlyWait(10L,TimeUnit.SECONDS);

        try {
            MobileElement loginLabelElm = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElm.click();
            //input username
            MobileElement email = androidDriver.findElementByAccessibilityId("input-email");
            email.sendKeys("abs@gmail.com");
            //input password
            MobileElement password = androidDriver.findElementByAccessibilityId("input-password");
            password.sendKeys("passwordne");
            MobileElement btnLogin = androidDriver.findElementByAccessibilityId("button-LOGIN");
            btnLogin.click();

            WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
            androidDriver.activateApp("com.android.settings");
            androidDriver.findElementByXPath("//*[@text='Network & internet']").click();
            MobileElement isWifiOn = androidDriver.findElementByAccessibilityId("Wi‑Fi");
            isWifiOn.click();
            if(isWifiOn.getText().equalsIgnoreCase("ON")){
                isWifiOn.click();
            }

            androidDriver.activateApp("com.wdiodemoapp");
            Thread.sleep(3000);
            androidDriver.findElementByXPath("//*[@text='OK']").click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
//com.android.settings/com.android.settings.Settings
    ////*[@text='Network & internet']
}
