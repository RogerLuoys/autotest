package commonClient;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UiAppiumClient {

    private AppiumDriver driver;

    public void init() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        cap.setCapability("platformName", "Android"); //指定测试平台
        cap.setCapability("deviceName", "127.0.0.1:7555"); //指定测试机的ID,通过adb命令`adb devices`获取
        cap.setCapability("platformVersion", "6.0.1");

        //将上面获取到的包名和Activity名设置为值?????
        cap.setCapability("appPackage", "com.youdao.calculator");
        cap.setCapability("appActivity", "com.youdao.calculator.activities.MainActivity");

//        //A new session could not be created的解决方法
//        cap.setCapability("appWaitActivity", "com.meizu.flyme.calculator.Calculator");
//        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
//        cap.setCapability("sessionOverride", true);

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
    }

    @Test
    public void test() {
        try {
            init();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("")).sendKeys("test");

    }


}
