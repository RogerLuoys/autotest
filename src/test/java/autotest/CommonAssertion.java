package autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * 自定义断言
 * 也可以继承Assert类
 */
public class CommonAssertion {

    CommonUi ui;

    public CommonAssertion (CommonUi ui) {
        this.ui = ui;
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

    /**
     * 判断指定元素是否存在 (UI专用)
     * 如果找到多个元素，也算成功
     *
     * @param xpath 元素的xpath
     */
    public void isElementExist(String xpath) {
        if (ui.getDriver() == null) {
            Assert.fail("未启动webDriver");
            return;
        }
        List<WebElement> webElements = ui.getDriver().findElements(By.xpath(xpath));
        Assert.assertTrue(webElements.size() > 0);
    }

    /**
     * 判断指定元素是否存在 (UI专用)
     * xpath格式不对导致找不到，也算成功
     *
     * @param xpath 元素的xpath
     */
    public void isElementNotExist(String xpath) {
        if (ui.getDriver() == null) {
            Assert.fail("未启动webDriver");
            return;
        }
        List<WebElement> webElements = ui.getDriver().findElements(By.xpath(xpath));
        Assert.assertTrue(webElements.size() == 0);
    }

}
