package commonClient;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * 自定义断言
 * 也可以继承Assert类
 */
public class AssertionCommonClient {

    private UiCommonClient ui;

    public AssertionCommonClient() {}

    public AssertionCommonClient (UiCommonClient ui) {
        this.ui = ui;
    }

    /**
     * 校验actual是否存包含了expect
     *
     * @param actual 实际结果
     * @param expect 预期结果
     */
    public void isContains(String actual, String expect) {
        Assert.assertTrue(actual.contains(expect));
    }

    /**
     * 校验expect是否存包含了actual
     *
     * @param actual 实际结果
     * @param expect 预期结果
     */
    public void isBeContains(String actual, String expect) {
        Assert.assertTrue(expect.contains(actual));
    }

    /**
     * 校验字符串是否相等
     *
     * @param actual 实际结果
     * @param expect 预期结果
     */
    public void isEquals(String actual, String expect) {
        Assert.assertEquals(expect, actual);
    }

    /**
     * 校验数据是否被逻辑删除，删除则校验通过，未删除则校验失败
     *
     * @param actual 实际结果，0或false表示未删除
     */
    public void isDeleted(String actual) {
        if (actual.equalsIgnoreCase("0") || actual.equalsIgnoreCase("false")) {
            Assert.fail();
        } else {
            Assert.assertTrue(true);
        }
    }

    /**
     * 校验数据是否未被逻辑删除，删除则校验失败，未删除则校验通过
     *
     * @param actual 实际结果，0或false表示未删除
     */
    public void isNotDeleted(String actual) {
        if (actual.equalsIgnoreCase("0") || actual.equalsIgnoreCase("false")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    /**
     * 校验实际结果是否大于预期结果
     *
     * @param actual 实际结果，需要是数字字符
     * @param expect 预期结果，需要是数字字符
     */
    public void isGreater(String actual, String expect) {
        double actual1 = Double.parseDouble(actual);
        double expect1 = Double.parseDouble(expect);
        if (actual1 > expect1) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    /**
     * 校验实际结果是否小于预期结果
     *
     * @param actual 实际结果，需要是数字字符
     * @param expect 预期结果，需要是数字字符
     */
    public void isSmaller(String actual, String expect) {
        double actual1 = Double.parseDouble(actual);
        double expect1 = Double.parseDouble(expect);
        if (actual1 < expect1) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    /**
     * 判断指定元素是否存在 (UI专用)
     * 如果找到多个元素，也算成功
     *
     * @param xpath 元素的xpath
     */
    public void isXpathExist(String xpath) {
        if (this.ui.getDriver() == null) {
            Assert.fail("未启动webDriver");
            return;
        }
        List<WebElement> webElements = this.ui.getElements(xpath);
        Assert.assertTrue(webElements.size() > 0);
    }

    /**
     * 判断指定元素是否存在 (UI专用)
     * xpath格式不对导致找不到，也算成功
     *
     * @param xpath 元素的xpath
     */
    public void isXpathNotExist(String xpath) {
        if (this.ui.getDriver() == null) {
            Assert.fail("未启动webDriver");
            return;
        }
        List<WebElement> webElements = this.ui.getElements(xpath);
        Assert.assertEquals(webElements.size(), 0);
    }

}
