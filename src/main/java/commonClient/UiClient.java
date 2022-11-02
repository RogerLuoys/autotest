package commonClient;

import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UiClient {

    // 显式等待最大时间
    private final Duration actionDuration = Duration.ofSeconds(30L);
    // 隐式等待最大时间
    private final Duration elementDuration = Duration.ofSeconds(10L);
    private WebDriverWait webDriverWait = null;
    private WebDriver driver = null;

//    private static void killLinuxProcess(String processName) {
//    }
//    private static void killWindowsProcess(String processName) {
//    }

    /**
     * 返回driver实例
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * 初始化，参数默认
     */
    public void init() {
        if (driver != null) {
            return;
        }
        // 设置启动参数
        ChromeOptions chromeOptions = new ChromeOptions();
//        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
//            killLinuxProcess("chromedriver");
//            // 无地址栏
//            chromeOptions.addArguments("--kiosk");
//        } else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//            killWindowsProcess("chromedriver");
//        }
//            chromeOptions.addArguments("window-size=1920*1080");
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
        chromeOptions.addArguments("--headless");
        // 去掉Chrome提示受到自动软件控制
//        chromeOptions.addArguments("--disable-infobars");
//        chromeOptions.addArguments("log-level=3");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setLogLevel(ChromeDriverLogLevel.WARNING);
        driver = new ChromeDriver(chromeOptions);
        // 针对查找页面元素的隐式等待
        driver.manage().timeouts().implicitlyWait(elementDuration);
        webDriverWait = new WebDriverWait(driver, actionDuration);
//        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);
    }

    /**
     * 初始化，参数默认
     */
    public void init(String... options) {
        if (driver == null) {
            // 设置启动参数
            ChromeOptions chromeOptions = new ChromeOptions();
//            if (System.getProperty("os.name").toLowerCase().contains("linux")) {
//                killLinuxProcess("chromedriver");
//            } else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//                killWindowsProcess("chromedriver");
//            }
            chromeOptions.addArguments(Arrays.asList(options));
            driver = new ChromeDriver(chromeOptions);
            // 针对查找页面元素的隐式等待
            driver.manage().timeouts().implicitlyWait(elementDuration);}
    }


    public void initH5() {
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
        // 针对查找页面元素的隐式等待
        driver.manage().timeouts().implicitlyWait(elementDuration);
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

//    /**
//     * 获取同类别的自动化元素列表
//     *
//     * @param locator 自选元素定位方式
//     * @return 所有符合条件的元素
//     */
//    private List<WebElement> getElements(By locator) {
////        forceWait(forceTimeOut);
//        List<WebElement> elements = null;
//        // 多找几次元素
//        for (int i = 0; i < 3; i++) {
//            // 首次寻找不用等待
//            if (i != 0) {
//                forceWait(3);
//            }
//            elements = driver.findElements(locator);
//            if (elements != null && elements.size() > 0) {
//                return elements;
//            }
//        }
//        return elements;
//    }

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

    /**
     * 鼠标点击指定元素
     *
     * @param locator 自选元素定位方式
     */
    private void click(By locator, int index) {
//        forceWait(forceTimeOut);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, actionDuration);
//        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        WebElement webElement = getElements(locator).get(index);
        Actions actions = new Actions(driver);
        actions.click(webElement);
        actions.perform();
    }

    /**
     * 鼠标点击指定元素
     *
     * @param xpath 元素的xpath
     */
    public void click(String xpath) {
        click(By.xpath(xpath), 0);
    }

    /**
     * 鼠标点击指定元素
     *
     * @param xpath 元素的xpath
     * @param index list下标，从0开始
     */
    public void click(String xpath, Integer index) {
        click(By.xpath(xpath), index);
    }

    /**
     * 往元素中输入字符
     *
     * @param locator 自选元素定位方式
     * @param key     输入的字符串
     */
    private void sendKey(By locator, String key) {
//        forceWait(forceTimeOut);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, actionDuration);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement webElement = getElement(locator);
        if (key.equalsIgnoreCase("{ENTER}")) {
            webElement.sendKeys("\ue007");
            return;
        } else if (key.equalsIgnoreCase("{TAB}")) {
            webElement.sendKeys("\ue004");
            return;
        }
        webElement.clear();
        Actions actions = new Actions(driver);
        actions.sendKeys(webElement, key);
        actions.perform();
    }

//    /**
//     * 往指定元素中输入字符
//     *
//     * @param element 元素对象
//     * @param key     输入的字符串
//     */
//    public void sendKey(WebElement element, CharSequence key) {
//        forceWait(forceTimeOut);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
//        webDriverWait.until(ExpectedConditions.visibilityOf(element));
//        Actions actions = new Actions(driver);
//        actions.sendKeys(element, key);
//        actions.perform();
//    }

    /**
     * 先清除输入框的内容，再往指定元素中输入字符
     *
     * @param xpath 元素的xpath
     * @param key   输入的字符串
     */
    public void sendKey(String xpath, String key) {
        sendKey(By.xpath(xpath), key);
    }

    /**
     * 鼠标移动到指定元素上
     *
     * @param locator 自选元素定位方式
     */
    private void moveToElement(By locator) {
//        forceWait(forceTimeOut);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, actionDuration);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        WebElement webElement = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();
    }

    /**
     * 鼠标移动到指定元素上
     *
     * @param xpath 元素的xpath
     */
    public void moveToElement(String xpath) {
        moveToElement(By.xpath(xpath));
    }

    /**
     * 先移动到控件位置，再点击
     *
     * @param locator 元素位置
     */
    private void moveAndClick(By locator) {
//        forceWait(forceTimeOut);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, actionDuration);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement webElement = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).click();
        actions.perform();
    }

    /**
     * 先鼠标移动到指定元素上，然后鼠标点击
     *
     * @param xpath 元素的xpath
     */
    public void moveAndClick(String xpath) {
        moveAndClick(By.xpath(xpath));
    }

    /**
     * 执行java script脚本
     *
     * @param jsExecString 脚本
     */
    public void execJs(String jsExecString) {
        ((JavascriptExecutor) driver).executeScript(jsExecString, new Object[0]);
    }

    public void execJs(WebElement webElement, String jsExecString) {
        ((JavascriptExecutor) driver).executeScript(jsExecString, new Object[]{webElement});
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
