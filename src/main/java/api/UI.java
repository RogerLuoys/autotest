package api;

import org.openqa.selenium.By;

public interface UI {

    void init(String url);

    void quit();

    void forceWait(Long second);

    void setTimeout(Long second);

    void click(By locator);

    void clickByXpath(String xpath);

    void sendKey(By locator, CharSequence key);

    void sendKeyByXpath(String xpath, CharSequence key);

    void moveToElement(By locator);

    void moveToElementByXpath(String xpath);

    void moveAndClick(By locator);

    void moveAndClickXpath(String xpath);
}
