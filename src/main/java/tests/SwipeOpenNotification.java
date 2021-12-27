package tests;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SwipeOpenNotification {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        try{
            androidDriver.manage().timeouts().implicitlyWait(3l, TimeUnit.SECONDS);
            //click Login label
            MobileElement loginLabelElm = androidDriver.findElementByAccessibilityId("Forms");
            loginLabelElm.click();

            Dimension size = androidDriver.manage().window().getSize();
            System.out.println("width: " + size.width + " height: " + size.height);
            int anchor = (int) (size.width * 0.2);
            int xStartPoint = (int) (size.width * 0.5);
            int yStartPoint = 0;
            int yEndPoint = (int) (size.height  * 0.4);

            //convert  to PointOption - Coorinates
            //pull
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xStartPoint, yEndPoint);

            TouchAction action = new TouchAction(androidDriver);
            action.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release().perform();

            Thread.sleep(4000);

            //

            //push
            action.press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(startPoint)
                    .release().perform();
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
