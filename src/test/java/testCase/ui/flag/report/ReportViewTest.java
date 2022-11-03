package testCase.ui.flag.report;

import testCase.ui.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReportViewTest extends FlagTestBase {

    @Test(description = "查看指定成长轨迹")
    void lookReport() {
        Reporter.log("进入成长轨迹");
        auto.ui.clickByJS("//span[text()='成长轨迹']");
        auto.ui.click("//span[text()='自动化成长轨迹测试']");

        Reporter.log("验证列表和详情");
        auto.assertion.isElementExist("//div[contains(text(), '获得总积分：1')]");
        auto.assertion.isElementExist("//div[contains(text(), '完成总任务：1')]");
        auto.assertion.isElementExist("//div[contains(text(), '自动化成长轨迹临时任务 自动化成长轨迹描述')]");
        auto.assertion.isElementExist("//div[contains(text(), '2021-03-31')]");
    }
}
