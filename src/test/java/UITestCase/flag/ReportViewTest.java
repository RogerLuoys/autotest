package UITestCase.flag;

import org.testng.Assert;
import org.testng.annotations.Test;
import supperTestClass.ui.UITestBase;

public class ReportViewTest extends UITestBase {

    @Test
    void lookReport() {
        //进入成长轨迹
        auto.ui.click("//span[text()='成长轨迹']");
        auto.ui.click("//span[text()='自动化成长轨迹测试']");
        //验证列表和详情
        Assert.assertTrue(auto.ui.isElementExist("//div[contains(text(), '获得总积分：1')]"), "验证总积分");
        Assert.assertTrue(auto.ui.isElementExist("//div[contains(text(), '完成总任务：1')]"), "验证总任务");
        Assert.assertTrue(auto.ui.isElementExist("//div[contains(text(), '自动化成长轨迹临时任务 自动化成长轨迹描述')]"), "验证任务备注");
        Assert.assertTrue(auto.ui.isElementExist("//div[contains(text(), '2021-03-31')]"), "验证任务日期");
    }
}
