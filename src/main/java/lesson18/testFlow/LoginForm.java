package lesson18.testFlow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lesson18.data.LoginCred;
import lesson18.pageObject.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginForm {

    private AppiumDriver<MobileElement> appiumDriver;
    private String username;
    private String password;

    public LoginForm(AppiumDriver<MobileElement> appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public LoginForm setData(LoginCred loginCred){
        this.username = loginCred.getUsername();
        this.password = loginCred.getPassword();
        return this;
    }

    public LoginForm loginValid(){
        appiumDriver.manage().timeouts().implicitlyWait(3l, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(appiumDriver);
        loginPage.clickOnLoginLbl();
        loginPage.inputUserName(this.username).inputPassword(this.password).clickOnLoginBtn();
        return this;
    }

}
