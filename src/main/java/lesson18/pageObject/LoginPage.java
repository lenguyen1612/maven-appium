package lesson18.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginPage {
    private AppiumDriver<MobileElement> appiumDriver;

    public LoginPage(AppiumDriver<MobileElement> appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public LoginPage inputUserName(String username){
        MobileElement element = appiumDriver.findElementByAccessibilityId("input-email");
        element.sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password){
        MobileElement element = appiumDriver.findElementByAccessibilityId("input-password");
        element.sendKeys(password);
        return this;
    }

    public LoginPage clickOnLoginBtn(){
        MobileElement element = appiumDriver.findElementByAccessibilityId("button-LOGIN");
        element.click();
        return this;
    }

    public LoginPage clickOnLoginLbl(){
        MobileElement element = appiumDriver.findElementByAccessibilityId("Login");
        element.click();
        return this;
    }
}
