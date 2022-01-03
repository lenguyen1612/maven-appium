package tests;

import contexts.AppContexts;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HandleHybirdContext implements AppContexts {

    public static void main(String[] args) {

        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        androidDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        try {
            //Click on WebView
            MobileElement webviewLabelElement = androidDriver.findElementByAccessibilityId("Webview");
            webviewLabelElement.click();

            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);

            androidDriver.getContextHandles().forEach(context -> {
                System.out.println(context);
            });
            androidDriver.context(WEB);
            WebElement navToggleBtn = androidDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtn.click();

            List<MobileElement> menuItems = androidDriver.findElementsByCssSelector(".menu__list-item");
            List<Menu> listMenu = new ArrayList<Menu>();
            if(menuItems.isEmpty()) throw new RuntimeException(("[ERR] Menu items is empty!"));

            menuItems.forEach(menuItem -> {
                String itemText = menuItem.getText();
                String itemHyperlink = menuItem.getAttribute("href");
                if(StringUtils.isEmpty(itemText)) {
                    listMenu.add(new Menu("GitHub", itemHyperlink));
                }else{
                    listMenu.add(new Menu(itemText, itemHyperlink));
                }
            });

            listMenu.forEach(item -> {
                System.out.println(item);
            });

            //Switch back native
            androidDriver.context(NATIVE);
            androidDriver.findElementByAccessibilityId("Login").click();

            //Run in background
            androidDriver.runAppInBackground(Duration.ofSeconds(3));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
    
    private static ExpectedCondition<Boolean> moreThanOneContext(AppiumDriver<MobileElement> appiumDriver){
        final ExpectedCondition<Boolean> expectedCondition = new ExpectedCondition<Boolean>() {
            //@NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver input) {
                return appiumDriver.getContextHandles().size() > 1;
            }
        };
        return expectedCondition;
    }

    private static class Menu{
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        private String attribute;
        private Menu(String text, String attribute){
            this.text = text;
            this.attribute = attribute;
        }

        @Override
        public String toString(){

            return "Text: " + getText() + " Hyperlink: "+ getAttribute();
        }
    }
}
