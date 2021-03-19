package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface UI {

    void init(String url);

    void refresh(String url);

    void quit();

    void forceWait(Long second);

    void setTimeout(Long second);

    WebElement getElement(By locator);

    List<WebElement> getElements(By locator);

    void click(By locator);

    void click(WebElement element);

    void clickByXpath(String xpath);

    void sendKey(By locator, CharSequence key);

    void sendKey(WebElement element, CharSequence key);

    void sendKeyByXpath(String xpath, CharSequence key);

    void moveToElement(By locator);

    void moveToElementByXpath(String xpath);

    void moveAndClick(By locator);

    void moveAndClickXpath(String xpath);
}
