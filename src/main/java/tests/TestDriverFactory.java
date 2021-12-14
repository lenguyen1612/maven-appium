package tests;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.time.Duration;
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
//            password.click();

            Dimension size = androidDriver.manage().window().getSize();
            System.out.println("width: " + size.width + " height: " + size.height);
            int xStartPoint = (int) (size.width * 0.3);
            int yStartPoint = (int) (size.height  * 0.9);
            int yEndPoint = (int) (size.height  * 0.4);

            //convert  to PointOption - Coorinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xStartPoint, yEndPoint);

            TouchAction action = new TouchAction(androidDriver);
            action.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(5)))
                    .moveTo(endPoint)
                    .release().perform();

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
