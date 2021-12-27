package tests;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SwipeHrizontally_Lap14 {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        androidDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        MobileElement swipeLabel = androidDriver.findElementByAccessibilityId("Swipe");
        swipeLabel.click();

        WebDriverWait wait = new WebDriverWait(androidDriver, 10L);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Swipe horizontal']")));

        try {

            Dimension size = androidDriver.manage().window().getSize();
            System.out.println("width: " + size.width + " height: " + size.height);
            int anchor = (int) (size.width * 0.2);
            int xStartPoint = (int) (size.width * 0.2);
            int xEndPoint = (int) (size.width * 0.7);
            int yStartPoint = (int) (size.height * 0.5);
//            int yEndPoint = (int) (size.height * 0.4);

            //convert  to PointOption - Coorinates
            //pull
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yStartPoint);

            TouchAction action = new TouchAction(androidDriver);
            action.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release().perform();

            Thread.sleep(4000);
            final int MAX_SWIPE_TIME = 4;
            int swipeTime = 0;

            while (swipeTime < MAX_SWIPE_TIME) {
                List<MobileElement> matchedElems = androidDriver.findElementsByXPath("//*[@text='EXTENDABLE']");
                if (!matchedElems.isEmpty()) break;
                //push
                action.press(endPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                        .moveTo(startPoint)
                        .release().perform();

                swipeTime++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
