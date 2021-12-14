package caps;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {

    String PLATFORM_NAME = "platformName";
    String AUTOMATION_NAME = "uiautomator2";
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
}