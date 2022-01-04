package lesson18.test;

import lesson18.core.BaseTest;
import lesson18.data.DataObjectBuilder;
import lesson18.data.LoginCred;
import lesson18.testFlow.LoginForm;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginCases1 extends BaseTest {

    @Test(dataProvider = "loginCerd")
    public void loginValid(LoginCred loginCred){
        LoginForm loginForm = new LoginForm(appiumDriver);
        loginForm.setData(loginCred).loginValid();
    }

    @Test(dataProvider = "loginCerd")
    public void loginInValid(LoginCred loginCred){
        LoginForm loginForm = new LoginForm(appiumDriver);
        loginForm.setData(loginCred).loginValid();
    }

    @DataProvider
    public LoginCred[] loginCerd(){
        DataObjectBuilder data = new DataObjectBuilder();
        LoginCred[] listData = data.buildDataObject("/loginCerd.json", LoginCred[].class);
        return listData;
    }
}
