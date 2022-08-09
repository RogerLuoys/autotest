package ui;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Deprecated
@Slf4j
public class UISelenium {

    private final Long DEFAULT_WAIT_TIME = 30L;
    private WebDriver driver = null;
    private int forceTimeOut = 1;

    /**
     * 初始化
     *
     * @param url 被测网站主页或登录页
     */
    public void init(String url) {
        this.driver = new ChromeDriver();
        this.driver.get(url);
        this.driver.manage().window().maximize();
    }

    /**
     * 刷新页面
     *
     * @param url 被测网站主页或登录页
     */
    public void refresh(String url) {
        this.driver.get(url);
        forceWait(3);
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
    }

    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    public void forceWait(int second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            log.error("\n---->线程睡眠异常");
//            e.printStackTrace();
        }
    }

    /**
     * 设置显式等待的统一时间-默认30s
     *
     * @param second 单位秒
     */
    public void setTimeout(int second) {
        this.forceTimeOut = second;
    }

    /**
     * 获取自动化元素
     *
     * @param locator 自选元素定位方式
     * @return 返回一个元素
     */
    public WebElement getElement(By locator) {
        forceWait(forceTimeOut);
        return driver.findElement(locator);
    }

    /**
     * 获取同类别的自动化元素列表
     *
     * @param locator 自选元素定位方式
     * @return 所有符合条件的元素
     */
    public List<WebElement> getElements(By locator) {
        forceWait(forceTimeOut);
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
    public void click(By locator) {
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        Actions actions = new Actions(driver);
        actions.click(webElement);
        actions.perform();
    }

    /**
     * 鼠标点击指定元素
     *
     * @param element 元素对象
     */
    public void click(WebElement element) {
        forceWait(forceTimeOut);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.click(element);
        actions.perform();
    }

    /**
     * 鼠标点击指定元素
     *
     * @param xpath 元素的xpath
     */
    public void click(String xpath) {
        click(By.xpath(xpath));
    }

    /**
     * 往元素中输入字符
     *
     * @param locator 自选元素定位方式
     * @param key     输入的字符串
     */
    public void sendKey(By locator, CharSequence key) {
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        Actions actions = new Actions(driver);
        actions.sendKeys(webElement, key);
        actions.perform();
    }

    /**
     * 往指定元素中输入字符
     *
     * @param element 元素对象
     * @param key     输入的字符串
     */
    public void sendKey(WebElement element, CharSequence key) {
        forceWait(forceTimeOut);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        Actions actions = new Actions(driver);
        actions.sendKeys(element, key);
        actions.perform();
    }

    /**
     * 往指定元素中输入字符
     *
     * @param xpath 元素的xpath
     * @param key   输入的字符串
     */
    public void sendKey(String xpath, CharSequence key) {
        sendKey(By.xpath(xpath), key);
    }

    /**
     * 先清除输入框的内容，再往指定元素中输入字符
     *
     * @param locator 自选元素定位方式
     * @param key     输入的字符串
     */
    public void sendKeyWithClear(By locator, CharSequence key) {
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        Actions actions = new Actions(driver);
        actions.sendKeys(webElement, key);
        actions.perform();
    }

    /**
     * 先清除输入框的内容，再往指定元素中输入字符
     *
     * @param element 元素对象
     * @param key     输入的字符串
     */
    public void sendKeyWithClear(WebElement element, CharSequence key) {
        forceWait(forceTimeOut);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        Actions actions = new Actions(driver);
        actions.sendKeys(element, key);
        actions.perform();
    }

    /**
     * 先清除输入框的内容，再往指定元素中输入字符
     *
     * @param xpath 元素的xpath
     * @param key   输入的字符串
     */
    public void sendKeyWithClear(String xpath, CharSequence key) {
        sendKeyWithClear(By.xpath(xpath), key);

    }

    /**
     * 鼠标移动到指定元素上
     *
     * @param locator 自选元素定位方式
     */
    public void moveToElement(By locator) {
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
     * @param locator 元素位置
     */
    public void moveAndClick(By locator) {
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

    /**
     * 判断指定元素是否存在，如果找到多个元素，也返回true
     *
     * @param locator 自选元素定位方式
     * @return 存在-true，不存在-false
     */
    public Boolean isElementExist(By locator) {
        List<WebElement> webElementList = getElements(locator);
        return webElementList.size() > 0;
    }

    /**
     * 判断指定元素是否存在，如果找到多个元素，也返回true
     *
     * @param xpath 元素的xpath
     * @return 存在-true，不存在-false
     */
    public Boolean isElementExist(String xpath) {
        return isElementExist(By.xpath(xpath));
    }


    public void execJs(String jsExecString) {
        ((JavascriptExecutor)this.driver).executeScript(jsExecString, new Object[0]);
    }

    public void execJs(WebElement webElement, String jsExecString) {
        ((JavascriptExecutor)this.driver).executeScript(jsExecString, new Object[]{webElement});
    }


    public void deleteCookies() {
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
