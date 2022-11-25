package testCase.ui.flag.task;

import testCase.ui.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class NewTaskDailyWithoutFlagTest extends FlagTestBase {

    // 只能有一个@Test注解
    @Test(description = "新增不关联flag的临时任务")
    void newTaskDailyWithoutFlag() {
        // 数据准备，还原上次执行结果
        auto.sql.flag("delete from task_daily where task_daily_name='自动化测试临时任务001';");

        Reporter.log("进入每日任务模块");
        auto.ui.click("//span[text()='每日任务']");

        Reporter.log("点下个月第一天");
        auto.ui.click("//span[contains(text(), '下个月')]");
        auto.ui.click("//span[contains(text(), '01')]");

        Reporter.log("新增临时任务，不关联flag");
        auto.ui.sendKey("//input[@placeholder='请输入任务名']", "自动化测试临时任务001");
        auto.ui.sendKey("//textarea[@placeholder='请输入描述']", "自动化临时任务描述001");
        auto.ui.click("//span[text()='保存']");
        auto.util.sleep(3);

        Reporter.log("验证添加的临时任务详情");
        auto.ui.click("//td[contains(string(), '01')]//span[text()='今日有任务']");
        Reporter.log("验证任务名");
        auto.assertion.isXpathExist("//span[text()='自动化测试临时任务001']");
        Reporter.log("验证临时任务描述");
        auto.assertion.isXpathExist("//div[text()='自动化临时任务描述001']");
        Reporter.log("验证临时任务默认积分");
        auto.assertion.isXpathExist("//div[text()='1']");
    }

}
