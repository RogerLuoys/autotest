package autotest.ui.flag.point;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import autotest.ui.UITestBase;

public class PointViewTest extends UITestBase {


    @Test(description = "查看积分收入列表")
    void lookPointIncrease() {
        Reporter.log("进入积分");
        auto.ui.click("//span[text()='积分']");

        Reporter.log("验证列表和详情");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='完成每日任务获得']"), "验证积分获得");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='1000']"), "验证积分获得");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化测试临时任务']"), "验证任务备注");
    }

    @Test(description = "查看积分使用列表")
    void lookPointDecrease() {
        Reporter.log("进入积分使用tab");
        auto.ui.click("//span[text()='积分']");
        auto.ui.click("//div[text()='积分使用']");

        Reporter.log("验证列表和详情");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化积分使用测试说明']"), "验证积分使用");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='10']"), "验证积分使用");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化积分使用测试备注']"), "验证任务使用");
    }
}
