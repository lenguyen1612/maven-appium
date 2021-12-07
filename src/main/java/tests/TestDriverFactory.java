package tests;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class TestDriverFactory {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        try{
            androidDriver.manage().timeouts().implicitlyWait(3l, TimeUnit.SECONDS);
            //click Login label
            MobileElement loginLabelElm = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElm.click();
            //input username
            MobileElement email = androidDriver.findElementByAccessibilityId("input-email");
            email.sendKeys("abs@gmail.com");
            //input password
            MobileElement password = androidDriver.findElementByAccessibilityId("input-password");
            password.sendKeys("passwordne");
            password.click();

            //click button login
//            Dimension size = androidDriver.manage().window().getSize();
//            int anchor = (int) (size.width * 0.2);
//            int startPoint = (int) (size.height  * 0.4);
//            int endPoint = (int) (size.height  * 0.1);
//            TouchAction action = new TouchAction(androidDriver);
//            action.press(point(startPoint, anchor))
//                    .waitAction(waitOptions(ofMillis(1000)))
//                    .moveTo(point(endPoint, anchor))
//                    .release().perform();
            MobileElement btnLogin = androidDriver.findElementByAccessibilityId("button-LOGIN");
            btnLogin.click();
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
