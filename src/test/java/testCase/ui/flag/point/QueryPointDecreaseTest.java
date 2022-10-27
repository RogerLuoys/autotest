package testCase.ui.flag.point;

import testCase.ui.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class QueryPointDecreaseTest extends FlagTestBase {

    @Test(description = "查看积分使用列表")
    void lookPointDecrease() {
        Reporter.log("进入积分使用tab");
        auto.ui.click("//span[text()='积分']");
        auto.ui.click("//div[text()='积分使用']");

        Reporter.log("验证列表和详情");
        auto.assertion.isElementExist("//div[text()='自动化积分使用测试说明']");
        auto.assertion.isElementExist("//div[text()='10']");
        auto.assertion.isElementExist("//div[text()='自动化积分使用测试备注']");

    }

}
