package testCaseUI.point;

import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.ui.UITestBase;

public class PointViewTest extends UITestBase {


    @Test
    void lookPointIncrease() {
        //进入积分
        auto.ui.click("//span[text()='积分']");
        //验证列表和详情
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='完成每日任务获得']"), "验证积分获得");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='1000']"), "验证积分获得");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化测试临时任务']"), "验证任务备注");
    }

    @Test
    void lookPointDecrease() {
        //进入积分使用tab
        auto.ui.click("//span[text()='积分']");
        auto.ui.click("//div[text()='积分使用']");
        //验证列表和详情
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化积分使用测试说明']"), "验证积分使用");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='10']"), "验证积分使用");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化积分使用测试备注']"), "验证任务使用");
    }
}
