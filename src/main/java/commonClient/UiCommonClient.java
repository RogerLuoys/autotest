package commonClient;

import io.appium.java_client.android.AndroidDriver;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
public class UiCommonClient {

    // 显式等待最大时间(有问题，偶尔会失效)
    private final Duration explicitDuration = Duration.ofSeconds(30L);
    // 隐式等待最大时间
    private final Duration implicitlyDuration = Duration.ofSeconds(10L);
    // 流畅等待最大时间
    private final Duration fluentDuration = Duration.ofSeconds(30L);
    private WebDriverWait webDriverWait = null;
    private Wait<WebDriver> wait = null;
    private WebDriver driver = null;
    private Actions actions = null;

    private static void killLinuxProcess(String processName) {
    }
    private static void killWindowsProcess(String processName) {
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


    /**
     * 返回driver实例
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * 用ChromeDriver初始化webdriver，参数默认，web模式；
     * 如果已初始化过，则跳过
     */
    public void initChromeWeb() {
        // 不为null则表示已初始化
        if (driver != null) {
            return;
        }
        // 设置启动参数
        ChromeOptions chromeOptions = new ChromeOptions();
        // 解决DevToolsActivePort文件不存在的报错
        chromeOptions.addArguments("--no-sandbox");
        // 设置启动浏览器空白页
        chromeOptions.addArguments("url=data:,");
        // 最大化
        chromeOptions.addArguments("--start-maximized");
        // 谷歌禁用GPU加速
        chromeOptions.addArguments("--disable-gpu");
        // 隐藏滚动条
        chromeOptions.addArguments("--hide-scrollbars");
        // 后台运行
//        chromeOptions.addArguments("--headless");
        // 去掉Chrome提示受到自动软件控制
        chromeOptions.addArguments("disable-infobars");
//        chromeOptions.addArguments("log-level=3");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setLogLevel(ChromeDriverLogLevel.OFF);
        driver = new ChromeDriver(chromeOptions);
        // 初始化actions
        actions = new Actions(driver);
        // 隐式等待设置
        driver.manage().timeouts().implicitlyWait(implicitlyDuration);
        // 显示等待初始化
        webDriverWait = new WebDriverWait(driver, explicitDuration);
        // 流畅等待初始化
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(fluentDuration)
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
//        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);
    }

    /**
     * 用ChromeDriver初始化webdriver，自定义参数
     * 如果已初始化过，则跳过
     */
    public void initChrome(String... options) {
        if (driver != null) {
            return;
        }
        Map<String, String> mobileEmulationMap = new HashMap<>();
        List<String> cOptions = new ArrayList<>();
        for (String option :  options) {
            // H5配置项
            if (option.matches(".+,.+")) {
                String[] var = option.split(",");
                mobileEmulationMap.put(var[0], var[1]);
            } else {
                cOptions.add(option);
            }
        }
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(cOptions);
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulationMap);

        driver = new ChromeDriver(chromeOptions);
        // 初始化actions
        actions = new Actions(driver);
        // 隐式等待设置
        driver.manage().timeouts().implicitlyWait(implicitlyDuration);
        // 显式等待初始化
        webDriverWait = new WebDriverWait(driver, explicitDuration);
        // 流畅等待初始化
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(fluentDuration)
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    /**
     * 用ChromeDriver初始化webdriver，参数默认，H5模式；
     * 如果已初始化过，则跳过
     */
    public void initChromeH5() {
        if (driver != null) {
            return;
        }
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-gpu");
        Map<String, String> mobileEmulationMap = new HashMap<>();
        mobileEmulationMap.put("deviceName", "Samsung Galaxy S8+");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulationMap);
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        // 初始化actions
        actions = new Actions(driver);
        // 隐式等待设置
        driver.manage().timeouts().implicitlyWait(implicitlyDuration);
        // 显示等待初始化
        webDriverWait = new WebDriverWait(driver, explicitDuration);
        // 流畅等待初始化
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(fluentDuration)
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    /**
     * 用AndroidDriver初始化webdriver，参数默认；
     * 如果已初始化过，则跳过
     */
    public void initAndroid() {
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

    /**
     * 刷新页面
     *
     * @param url 被测网站主页或登录页
     */
    public void openUrl(String url) {
        this.driver.get(url);
    }

    /**
     * 关闭浏览器且关闭资源
     */
    public void quit() {
        if (driver == null) {
            return;
        }
        driver.quit();
        driver = null;
    }

    /**
     * 获取自动化元素
     *
     * @param locator 自选元素定位方式
     * @return 返回一个元素
     */
    private WebElement getElement(By locator) {
        return getElements(locator).get(0);
    }

    /**
     * 获取同类别的自动化元素列表
     *
     * @param locator 自选元素定位方式
     * @return 所有符合条件的元素
     */
    private List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * 获取同类别的自动化元素列表
     *
     * @param xpath 元素的xpath
     * @return 所有符合条件的元素
     */
    public List<WebElement> getElements(String xpath) {
        return getElements(By.xpath(xpath));
    }

//    /**
//     * 鼠标点击指定元素
//     *
//     * @param locator 自选元素定位方式
//     */
//    private void click(By locator, int index) {
////        forceWait(forceTimeOut);
////        WebDriverWait webDriverWait = new WebDriverWait(driver, actionDuration);
////        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        WebElement webElement = wait.until(driver -> driver.findElements(locator).get(index));
////        WebElement webElement = getElements(locator).get(index);
//        actions.click(webElement);
//        actions.perform();
//    }

    /**
     * 鼠标点击指定元素
     *
     * @param xpath 元素的xpath
     */
    public void click(String xpath) {
        click(xpath, 0);
    }

    /**
     * 鼠标点击指定元素
     *
     * @param xpath 元素的xpath
     * @param index list下标，从0开始
     */
    public void click(String xpath, Integer index) {
        // 这里不能用显式等待，有的控件等不到，却可以点击
        WebElement webElement = wait.until(driver -> driver.findElements(By.xpath(xpath)).get(index));
        actions.click(webElement);
        actions.perform();

    }

    /**
     * 通过js的形式点击页面元素
     * @param xpath 页面元素的xpath
     */
    public void clickByJS(String xpath) {
        executeJS(xpath, "arguments[0].click();");
    }

//    /**
//     * 往元素中输入字符
//     *
//     * @param locator 自选元素定位方式
//     * @param key     输入的字符串
//     */
//    private void sendKey(By locator, String key) {
//        // 这里不能直接隐式等待
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
//        WebElement webElement = getElement(locator);
//        if (key.equalsIgnoreCase("{ENTER}")) {
//            webElement.sendKeys("\ue007");
//            return;
//        } else if (key.equalsIgnoreCase("{TAB}")) {
//            webElement.sendKeys("\ue004");
//            return;
//        }
//        webElement.clear();
//        actions.sendKeys(webElement, key);
//        actions.perform();
//    }

    /**
     * 先清除输入框的内容，再往指定元素中输入字符
     *
     * @param xpath 元素的xpath
     * @param key   输入的字符串
     */
    public void sendKey(String xpath, String key) {
        WebElement webElement = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        // 再强制等会，比较稳定(显示、隐式、流畅等待都有小概率操作失败)
        sleep(500L);
        if (key.equalsIgnoreCase("{ENTER}")) {
            webElement.sendKeys("\ue007");
            return;
        } else if (key.equalsIgnoreCase("{TAB}")) {
            webElement.sendKeys("\ue004");
            return;
        }
        webElement.clear();
        actions.sendKeys(webElement, key);
        actions.perform();
    }

//    /**
//     * 鼠标移动到指定元素上
//     *
//     * @param locator 自选元素定位方式
//     */
//    private void moveToElement(By locator) {
////        forceWait(forceTimeOut);
////        WebDriverWait webDriverWait = new WebDriverWait(driver, actionDuration);
////        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
////        WebElement webElement = driver.findElement(locator);
//        WebElement webElement = wait.until(driver -> driver.findElement(locator));
//        actions.moveToElement(webElement);
//        actions.perform();
//    }

    /**
     * 鼠标移动到指定元素上
     *
     * @param xpath 元素的xpath
     */
    public void moveToElement(String xpath) {
        WebElement webElement = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        actions.moveToElement(webElement);
        actions.perform();
    }

//    /**
//     * 先移动到控件位置，再点击
//     *
//     * @param locator 元素位置
//     */
//    private void moveAndClick(By locator) {
////        forceWait(forceTimeOut);
////        WebDriverWait webDriverWait = new WebDriverWait(driver, actionDuration);
////        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
////        WebElement webElement = driver.findElement(locator);
//        WebElement webElement = wait.until(driver -> driver.findElement(locator));
//        actions.moveToElement(webElement).click();
//        actions.perform();
//    }

    /**
     * 先鼠标移动到指定元素上，然后鼠标点击
     *
     * @param xpath 元素的xpath
     */
    public void moveAndClick(String xpath) {
        WebElement webElement = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        actions.moveToElement(webElement).click();
        actions.perform();
    }

    /**
     * 执行java script脚本
     *
     * @param jsExecString 脚本
     */
    public void executeJS(String jsExecString) {
        ((JavascriptExecutor) driver).executeScript(jsExecString);
    }

    /**
     * 执行java script脚本
     *
     * @param xpath 元素xpath
     * @param jsExecString 脚本
     */
    public void executeJS(String xpath, String jsExecString) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript(jsExecString, webElement);
    }


    /**
     * 切换到最后一个标签页，并关闭其它；
     * 如果只有一个标签页，则不处理
     */
    public void switchTab() {
        Set<String> windows = driver.getWindowHandles();
        if (windows.size() <= 1) {
            return;
        }
        // 先关闭前面所有标签页
        for (int i = windows.size() - 2; i >= 0; i--) {
            driver.switchTo().window(windows.toArray()[i].toString());
            driver.close();
        }
        // 再切换至最后标签页
        driver.switchTo().window(windows.toArray()[windows.size() - 1].toString());
    }

    /**
     * 删除所有cookies
     */
    public void clearCookies() {
        this.driver.manage().deleteAllCookies();
    }

    public void addCookie(Cookie cookie) {
        this.driver.manage().addCookie(cookie);
    }

    public Cookie getCookieByName(String cookieName) {
        return this.driver.manage().getCookieNamed(cookieName);
    }

    public void refresh() {
        this.driver.navigate().refresh();
    }

    public void back() {
        this.driver.navigate().back();
    }

}
