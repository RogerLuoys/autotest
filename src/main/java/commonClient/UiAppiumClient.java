package commonClient;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UiAppiumClient {

    // 隐式等待最大时间
    private final Duration implicitlyDuration = Duration.ofSeconds(10L);
    private AppiumDriver driver = null;
    private Actions actions = null;

    /**
     * 进程睡眠，强制等待
     *
     * @param millis 等待的时间-单位豪秒
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android"); //指定测试平台
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555"); //指定测试机的ID,通过adb命令`adb devices`获取

//        cap.setCapability("app", "C:\\other\\q391m11market01xtg101s.apk");
        desiredCapabilities.setCapability("appPackage", "com.qmxsppa.novelreader");
        desiredCapabilities.setCapability("appActivity", "com.marketplaceapp.novelmatthew.mvp.ui.activity.main.AndroidAppActivity");

        //A new session could not be created的解决方法
//        cap.setCapability("appWaitActivity", "com.qmxsppa.novelreader");
//        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
//        cap.setCapability("sessionOverride", true);
        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // 初始化actions
        actions = new Actions(driver);
        // 隐式等待设置
        driver.manage().timeouts().implicitlyWait(implicitlyDuration);
    }

    public void quit() {
        if (driver == null) {
            return;
        }
        driver.quit();
        driver = null;
    }

    public void click(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

}
