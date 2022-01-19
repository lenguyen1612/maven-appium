package lesson18.core;


import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import lesson18.drivers.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
//lap 18 ko 9 xác như thầy dạy
//    public AppiumDriver<MobileElement> appiumDriver;
//    private DriverFactory driverFactory = new DriverFactory();
//
//    @BeforeClass(alwaysRun = true)
//    public void setup(){
//        driverFactory.startAppiumServer();
//        if(this.appiumDriver==null){
//            this.appiumDriver = driverFactory.getAndroidDriver();
//        }
//
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void afterClass(){
//       driverFactory.stopAppiumServer();
//    }
//lap 19

    private AppiumDriver<MobileElement> appiumDriver;

    private final List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private ThreadLocal<DriverFactoryEx> driverFactoryThreadLocal;
    String uuid, deviceName, systemPort;

    @BeforeTest(alwaysRun = true)
    @Parameters({"uuid", "deviceName", "systemPort"})
    public void setup(String uuid, String deviceName, String systemPort) {
        this.uuid = uuid;
        this.deviceName = deviceName;
        this.systemPort = systemPort;
        driverFactoryThreadLocal = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverFactoryEx = new DriverFactoryEx();
            driverThreadPool.add(driverFactoryEx);
            return driverFactoryEx;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driverFactoryThreadLocal.get().quitAppiumSession();
    }

    @AfterMethod(description = "Capture screenshot on failure")
    public void afterMethod(ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            //Get methodName
            String testMethodName = iTestResult.getTestName();
            //Location to save screenshots
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int h = calendar.get(Calendar.HOUR);
            int min = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            int minS = calendar.get(Calendar.MILLISECOND);

            String dateTimeTakeSS = y + "-" + m + "-" + "-" + d + "-" + h + "-" + min + "-" + second + "-" + minS;
            String nameScreenshot = testMethodName + "_" + dateTimeTakeSS;
            String fileLocation = System.getProperty("user.dir") + "/screenshot/" + nameScreenshot;

            File screenShot = driverFactoryThreadLocal.get().getAndroidDriver().getScreenshotAs(OutputType.FILE);
            try{
                FileUtils.copyFile(screenShot, new File(fileLocation));
                Path content = Paths.get(fileLocation);

                InputStream is = Files.newInputStream(content);
                Allure.addAttachment(testMethodName, is);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public AppiumDriver<MobileElement> getDriverAndroid() {
        System.out.println("uuid: " + uuid + " deviceName: " + deviceName + " systemPort: " + systemPort);
        if (appiumDriver == null) {
            appiumDriver = driverFactoryThreadLocal.get().getAndroidDriver(uuid, deviceName, systemPort);
        }
        return appiumDriver;
    }


}
