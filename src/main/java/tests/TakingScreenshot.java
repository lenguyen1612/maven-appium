package tests;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TakingScreenshot {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        String path = System.getProperty("user.dir").concat("/screenshots/");

        try{
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            androidDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);

            MobileElement loginLabelElm = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElm.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("button-LOGIN")));

            //Taking screenshot
            File base64 = androidDriver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(base64, new File(path.concat("LoginForm.png")));
            //specific an element
            MobileElement btnLogin = androidDriver.findElementByAccessibilityId("button-LOGIN");
            base64 = btnLogin.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(base64, new File(path.concat("ButtonLogin.png")));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
