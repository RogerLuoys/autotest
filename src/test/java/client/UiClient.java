package client;

import commonClient.UiCommonClient;
import org.openqa.selenium.Cookie;

/**
 * 相当于base page
 * 需要在UI相关套件执行前实例化内部的webdriver，且套件结束后关闭资源
 */
public class UiClient extends UiCommonClient {
    // 这里可以加自定义方法或参数

    private String baseURL = null;


    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    private void sleep(int second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    @Override
    public void openUrl(String url) {
        if (url.toLowerCase().startsWith("http")) {
            super.openUrl(url);
        } else {
            super.openUrl(baseURL + url);
        }
        sleep(3);
    }

    protected void sendAndEnter(String xpath, String key) {
        super.sendKey(xpath, key);
        super.sendKey(xpath, "{ENTER}");
    }


    public void addCookie(Cookie cookie) {
        super.getDriver().manage().addCookie(cookie);
        super.settings();
    }

    public Cookie getCookieByName(String cookieName) {
        return super.getDriver().manage().getCookieNamed(cookieName);
    }

    public void refresh() {
        super.getDriver().navigate().refresh();
    }

    public void back() {
        super.getDriver().navigate().back();
    }

}
