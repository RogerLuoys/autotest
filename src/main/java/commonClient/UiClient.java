package commonClient;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UiClient {

    private final Long DEFAULT_WAIT_TIME = 30L;
    private WebDriver driver = null;
    private int forceTimeOut = 1;

    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    private void forceWait(int second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            log.error("\n---->线程睡眠异常");
            e.printStackTrace();
        }
    }

    private static void killLinuxProcess(String processName) {
    }

    private static void killWindowsProcess(String processName) {

    }

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
        if (driver == null) {
            // 设置启动参数
            ChromeOptions chromeOptions = new ChromeOptions();
            if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                killLinuxProcess("chromedriver");
                chromeOptions.addArguments("--kiosk");
                chromeOptions.addArguments("--disable-dev-shm-usage");
            } else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                killWindowsProcess("chromedriver");
            }
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
            chromeOptions.addArguments("disable-infobars");
            driver = new ChromeDriver(chromeOptions);
        }
    }

    /**
     * 初始化，参数默认
     */
    public void init(String... options) {
        if (driver == null) {
            // 设置启动参数
            ChromeOptions chromeOptions = new ChromeOptions();
            if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                killLinuxProcess("chromedriver");
            } else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                killWindowsProcess("chromedriver");
            }
            chromeOptions.addArguments(Arrays.asList(options));
            driver = new ChromeDriver(chromeOptions);
        }
    }


    public void initH5() {
        log.info("start chrome  H5 browser..");
        Map<String, Object> chromeOptions = new HashMap();
        Map<String, String> mobileEmulation = new HashMap();
        mobileEmulation.put("deviceName", "Galaxy S5");
        chromeOptions.put("mobileEmulation", mobileEmulation);
        List<String> args = new ArrayList();
        args.add("--no-sandbox");
//        args.add("--headless");
        args.add("--disable-gpu");

        if (System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0) {
            args.add("--kiosk");
            args.add("--disable-dev-shm-usage");
        } else if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
            args.add("--kiosk");
        }

        chromeOptions.put("args", args);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("goog:chromeOptions", chromeOptions);

        try {
            this.driver = new ChromeDriver(capabilities);
            this.driver.manage().timeouts().implicitlyWait(1L, TimeUnit.MILLISECONDS);
            log.info(" chrome  H5 browser started");
        } catch (Exception var6) {
            log.error("启动 chrome H5 driver failed !");
            log.error(var6.getMessage());
        }

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
        driver.close();
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
        forceWait(forceTimeOut);
        List<WebElement> elements = null;
        // 多找几次元素
        for (int i = 0; i < 3; i++) {
            // 首次寻找不用等待
            if (i != 0) {
                forceWait(3);
            }
            elements = driver.findElements(locator);
            if (elements != null && elements.size() > 0) {
                return elements;
            }
        }
        return elements;
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
        forceWait(forceTimeOut);
        WebElement webElement = getElements(locator).get(index);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        Actions actions = new Actions(driver);
        actions.click(webElement);
        actions.perform();
    }

//    /**
//     * 鼠标点击指定元素
//     *
//     * @param element 元素对象
//     */
//    public void click(WebElement element) {
//        forceWait(forceTimeOut);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
//        Actions actions = new Actions(driver);
//        actions.click(element);
//        actions.perform();
//    }

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
        forceWait(forceTimeOut);
        WebElement webElement = getElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
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

//    /**
//     * 先清除输入框的内容，再往指定元素中输入字符
//     *
//     * @param locator 自选元素定位方式
//     * @param key     输入的字符串
//     */
//    public void sendKeyWithClear(By locator, CharSequence key) {
//        forceWait(forceTimeOut);
//        WebElement webElement = driver.findElement(locator);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
//        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
//        webElement.clear();
//        Actions actions = new Actions(driver);
//        actions.sendKeys(webElement, key);
//        actions.perform();
//    }

//    /**
//     * 先清除输入框的内容，再往指定元素中输入字符
//     *
//     * @param element 元素对象
//     * @param key     输入的字符串
//     */
//    public void sendKeyWithClear(WebElement element, CharSequence key) {
//        forceWait(forceTimeOut);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
//        webDriverWait.until(ExpectedConditions.visibilityOf(element));
//        element.clear();
//        Actions actions = new Actions(driver);
//        actions.sendKeys(element, key);
//        actions.perform();
//    }

//    /**
//     * 先清除输入框的内容，再往指定元素中输入字符
//     *
//     * @param xpath 元素的xpath
//     * @param key   输入的字符串
//     */
//    public void sendKeyWithClear(String xpath, CharSequence key) {
//        sendKeyWithClear(By.xpath(xpath), key);
//
//    }

    /**
     * 鼠标移动到指定元素上
     *
     * @param locator 自选元素定位方式
     */
    private void moveToElement(By locator) {
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
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
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
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

//    /**
//     * 判断指定元素是否存在，如果找到多个元素，也返回true
//     *
//     * @param locator 自选元素定位方式
//     * @return 存在-true，不存在-false
//     */
//    private Boolean isElementExist(By locator) {
//        List<WebElement> webElementList = getElements(locator);
//        return webElementList.size() > 0;
//    }

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

    public Cookie getCookieNamed(String cookieName) {
        return this.driver.manage().getCookieNamed(cookieName);
    }

    public void refresh() {
        this.driver.navigate().refresh();
    }

    public void back() {
        this.driver.navigate().back();
    }

}
