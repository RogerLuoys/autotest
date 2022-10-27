package testCase.ui.flag.point;

import testCase.ui.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class QueryPointIncreaseTest extends FlagTestBase {

    @Test(description = "查看积分收入列表")
    void lookPointIncrease() {
        Reporter.log("进入积分");
        auto.ui.click("//span[text()='积分']");

        Reporter.log("验证列表和详情");
        auto.assertion.isElementExist("//div[text()='完成每日任务获得']");
        auto.assertion.isElementExist("//div[text()='1000']");
        auto.assertion.isElementExist("//div[text()='自动化测试临时任务']");

    }

}
