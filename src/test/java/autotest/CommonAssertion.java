package autotest;

import common.AssertionClient;

/**
 * 自定义断言
 * 也可以继承Assert类
 */
public class CommonAssertion extends AssertionClient {

    CommonUi ui;

    public CommonAssertion (CommonUi ui) {
        super(ui);
//        this.ui = ui;
    }
//
//    public void isTrue(Boolean result) {
//        Assert.assertTrue(result);
//    }
//
//    public void isFalse(Boolean result) {
//        Assert.assertFalse(result);
//    }
//
//    /**
//     * 校验expect是否存在于actual中
//     *
//     * @param actual 实际结果
//     * @param expect 预期结果
//     */
//    public void isContains(String actual, String expect) {
//        Assert.assertTrue(actual.contains(expect));
//    }
//
//    /**
//     * 校验字符串是否相等
//     *
//     * @param actual 实际结果
//     * @param expect 预期结果
//     */
//    public void isEquals(String actual, String expect) {
//        Assert.assertTrue(actual.equals(expect));
//    }
//
//    /**
//     * 校验数据是否被逻辑删除，删除则校验通过，未删除则校验失败
//     *
//     * @param actual 实际结果，0或false表示未删除
//     */
//    public void isDeleted(String actual) {
//        if (actual.equalsIgnoreCase("0") || actual.equalsIgnoreCase("false")) {
//            Assert.fail();
//        } else {
//            Assert.assertTrue(true);
//        }
//    }
//
//    /**
//     * 校验实际结果是否大于预期结果
//     *
//     * @param actual 实际结果，需要是数字字符
//     * @param expect 预期结果，需要是数字字符
//     */
//    public void isGreater(String actual, String expect) {
//        double actual1 = Double.parseDouble(actual);
//        double expect1 = Double.parseDouble(expect);
//        if (actual1 > expect1) {
//            Assert.assertTrue(true);
//        } else {
//            Assert.fail();
//        }
//    }
//
//    /**
//     * 判断指定元素是否存在 (UI专用)
//     * 如果找到多个元素，也算成功
//     *
//     * @param xpath 元素的xpath
//     */
//    public void isElementExist(String xpath) {
//        if (ui.getDriver() == null) {
//            Assert.fail("未启动webDriver");
//            return;
//        }
//        List<WebElement> webElements = ui.getElements(xpath);
//        Assert.assertTrue(webElements.size() > 0);
//    }
//
//    /**
//     * 判断指定元素是否存在 (UI专用)
//     * xpath格式不对导致找不到，也算成功
//     *
//     * @param xpath 元素的xpath
//     */
//    public void isElementNotExist(String xpath) {
//        if (ui.getDriver() == null) {
//            Assert.fail("未启动webDriver");
//            return;
//        }
//        List<WebElement> webElements = ui.getElements(xpath);
//        Assert.assertTrue(webElements.size() == 0);
//    }

}
