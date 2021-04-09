package testCaseUI.task;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.ui.UITestBase;


public class TaskDailyNewTest extends UITestBase {

    @BeforeClass
    public void resetData() {
        auto.flagDB.delete("delete from task_daily where task_daily_name='自动化测试临时任务001';");
        auto.flagDB.delete("delete from task_daily where task_daily_name='自动化测试临时任务002';");
    }

    @Test(description = "新增不关联flag的临时任务")
    void newTaskDailyWithoutFlag() {
        Reporter.log("进入每日任务模块");
        auto.ui.click("//span[text()='每日任务']");

        Reporter.log("点下个月第一天");
        auto.ui.click("//span[contains(text(), '下个月')]");
        auto.ui.click("//span[contains(text(), '01')]");

        Reporter.log("新增临时任务，不关联flag");
        auto.ui.sendKey("//input[@placeholder='请输入任务名']", "自动化测试临时任务001");
        auto.ui.sendKey("//textarea[@placeholder='请输入描述']", "自动化临时任务描述001");
        auto.ui.click("//span[text()='保存']");
        auto.ui.forceWait(3);

        Reporter.log("验证添加的临时任务详情");
        auto.ui.click("//td[contains(string(), '01')]//span[text()='今日有任务']");
        Assert.assertTrue(auto.ui.isElementExist("//span[text()='自动化测试临时任务001']"), "验证临时任务名称");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化临时任务描述001']"), "验证临时任务描述");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='1']"), "验证临时任务默认积分");
    }

    @Test(description = "新增关联flag的临时任务")
    void newTaskDailyWithFlag() {
        Reporter.log("进入每日任务模块");
        auto.ui.click("//span[text()='每日任务']");

        Reporter.log("点下个月第二天");
        auto.ui.click("//span[contains(text(), '下个月')]");
        auto.ui.click("//span[contains(text(), '02')]");

        Reporter.log("新增临时任务，关联flag");
        auto.ui.sendKey("//input[@placeholder='请输入任务名']", "自动化测试临时任务002");
        auto.ui.sendKey("//textarea[@placeholder='请输入描述']", "自动化临时任务描述002");
        auto.ui.sendKey("//input[@placeholder='请输入要关联的flagId']", "116167499163458");
        auto.ui.click("//span[text()='保存']");
        auto.ui.forceWait(3);

        Reporter.log("验证添加的临时任务详情");
        auto.ui.click("//td[contains(string(), '02')]//span[text()='今日有任务']");
        Assert.assertTrue(auto.ui.isElementExist("//span[text()='自动化测试临时任务002']"), "验证临时任务名称");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化临时任务描述002']"), "验证临时任务描述");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='1']"), "验证临时任务默认积分");
    }
}
