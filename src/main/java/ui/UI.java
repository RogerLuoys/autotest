package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface UI {

    /**
     * 初始化
     * @param url 被测网站主页或登录页
     */
    void init(String url);

    /**
     * 刷新页面
     * @param url 被测网站主页或登录页
     */
    void refresh(String url);

    /**
     * 关闭浏览器且关闭资源
     */
    void quit();

    /**
     * 进程睡眠，强制等待
     * @param second 等待的时间-单位秒
     */
    void forceWait(Long second);

    /**
     * 设置显式等待的统一时间-默认30s
     * @param second 单位秒
     */
    void setTimeout(Long second);

    /**
     * 获取自动化元素
     * @param locator 自选元素定位方式
     * @return 返回一个元素
     */
    WebElement getElement(By locator);

    /**
     * 获取同类别的自动化元素列表
     * @param locator 自选元素定位方式
     * @return 所有符合条件的元素
     */
    List<WebElement> getElements(By locator);

    /**
     * 获取同类别的自动化元素列表
     * @param xpath 元素的xpath
     * @return 所有符合条件的元素
     */
    List<WebElement> getElements(String xpath);

    /**
     * 鼠标点击指定元素
     * @param locator 自选元素定位方式
     */
    void click(By locator);

    /**
     * 鼠标点击指定元素
     * @param element 元素对象
     */
    void click(WebElement element);

    /**
     * 鼠标点击指定元素
     * @param xpath 元素的xpath
     */
    void click(String xpath);

    /**
     * 往元素中输入字符
     * @param locator 自选元素定位方式
     * @param key 输入的字符串
     */
    void sendKey(By locator, CharSequence key);

    /**
     * 往指定元素中输入字符
     * @param element 元素对象
     * @param key 输入的字符串
     */
    void sendKey(WebElement element, CharSequence key);

    /**
     * 往指定元素中输入字符
     * @param xpath 元素的xpath
     * @param key 输入的字符串
     */
    void sendKey(String xpath, CharSequence key);

    /**
     * 先清除输入框的内容，再往指定元素中输入字符
     * @param locator 自选元素定位方式
     * @param key 输入的字符串
     */
    void sendKeyWithClear(By locator, CharSequence key);

    /**
     * 先清除输入框的内容，再往指定元素中输入字符
     * @param element 元素对象
     * @param key 输入的字符串
     */
    void sendKeyWithClear(WebElement element, CharSequence key);

    /**
     * 先清除输入框的内容，再往指定元素中输入字符
     * @param xpath 元素的xpath
     * @param key 输入的字符串
     */
    void sendKeyWithClear(String xpath, CharSequence key);

    /**
     * 鼠标移动到指定元素上
     * @param locator 自选元素定位方式
     */
    void moveToElement(By locator);

    /**
     * 鼠标移动到指定元素上
     * @param xpath 元素的xpath
     */
    void moveToElement(String xpath);

    /**
     * 先鼠标移动到指定元素上，然后鼠标点击
     * @param locator 自选元素定位方式
     */
    void moveAndClick(By locator);

    /**
     * 先鼠标移动到指定元素上，然后鼠标点击
     * @param xpath 元素的xpath
     */
    void moveAndClick(String xpath);

    /**
     * 判断指定元素是否存在，如果找到多个元素，也返回true
     * @param locator 自选元素定位方式
     * @return 存在-true，不存在-false
     */
    Boolean isElementExist(By locator);

    /**
     * 判断指定元素是否存在，如果找到多个元素，也返回true
     * @param xpath 元素的xpath
     * @return 存在-true，不存在-false
     */
    Boolean isElementExist(String xpath);
}
