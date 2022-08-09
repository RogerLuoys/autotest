package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AssertionClient {

    private WebDriver driver = null;

    AssertionClient() {}

    AssertionClient(WebDriver driver) {
        this.driver = driver;
    }

    public void isTrue(Boolean result) {
        Assert.assertTrue(result);
    }

    public void isFalse(Boolean result) {
        Assert.assertFalse(result);
    }

    public void isContains(String actual, String expect) {
        Assert.assertTrue(actual.contains(expect));
    }

    public void isEquals(String actual, String expect) {
        Assert.assertTrue(actual.equals(expect));
    }

    public void isElementExist(String xpath) {
        List<WebElement> webElements = driver.findElements(By.xpath(xpath));
        Assert.assertTrue(webElements.size() > 0);
    }

    public void isElementNotExist(String xpath) {
        List<WebElement> webElements = driver.findElements(By.xpath(xpath));
        Assert.assertTrue(webElements.size() == 0);
    }

}
