package tests;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class SwipeVertically {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        try{
            androidDriver.manage().timeouts().implicitlyWait(3l, TimeUnit.SECONDS);
            //click Login label
            MobileElement loginLabelElm = androidDriver.findElementByAccessibilityId("Forms");
            loginLabelElm.click();
//            password.click();

            //click button login
//            wait element displayed
            WebDriverWait wait = new WebDriverWait(androidDriver, 3L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

            Dimension size = androidDriver.manage().window().getSize();
            System.out.println("width: " + size.width + " height: " + size.height);
            int anchor = (int) (size.width * 0.2);
            int xStartPoint = (int) (size.width * 0.5);
            int yStartPoint = (int) (size.height  * 0.9);
            int yEndPoint = (int) (size.height  * 0.4);

            //convert  to PointOption - Coorinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xStartPoint, yEndPoint);

            TouchAction action = new TouchAction(androidDriver);
            action.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release().perform();

            MobileElement btnLogin = androidDriver.findElementByAccessibilityId("button-Active");
            btnLogin.click();
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
