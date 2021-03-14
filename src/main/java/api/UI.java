package api;

import org.openqa.selenium.By;

public interface UI {

    void init();

    void setTimeout(int second);

    void click(By locator);

    void clickByXpath(String xpath);

    void sendKey(By locator);

    void sendKeyByXpath(String xpath);

    void moveToElement(By locator);

    void moveToElementByXpath(String xpath);
}
