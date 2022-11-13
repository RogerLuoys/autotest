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

    public void init() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        desiredCapabilities.setCapability("platformName", "Android"); //指定测试平台
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555"); //指定测试机的ID,通过adb命令`adb devices`获取
//        cap.setCapability("platformVersion", "6.0.1");

//        cap.setCapability("app", "C:\\other\\q391m11market01xtg101s.apk");
        //将上面获取到的包名和Activity名设置为值?????
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

    @Test
    public void test() {
        init();
        // 全民阅读官网apk
        driver.findElement(By.xpath("//*[@text='同意并进入']")).click();
        driver.findElement(By.xpath("//*[@text='立即进入']")).click();
        driver.findElement(By.xpath("//*[@text='好的']")).click();
    }


}
