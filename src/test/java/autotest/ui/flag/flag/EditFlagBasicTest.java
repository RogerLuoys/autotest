package autotest.ui.flag.flag;

import autotest.ui.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class EditFlagBasicTest extends FlagTestBase {

    @Test(description = "编辑flag基本信息")
    void editBasic() {
        Reporter.log("还原数据");
        auto.sql.flag("update flag set description=null, expected=null, actual=null, priority=1 where flag_id='116165510085692';");

        Reporter.log("打开详情");
        auto.function.openFlagDetail("自动化FLAG基本信息编辑测试-勿删");

        Reporter.log("编辑优先级");
        auto.ui.click("//input[@placeholder='选择优先级']");
        auto.ui.click("//span[text()='非常重要']");

        Reporter.log("编辑描述");
        auto.ui.sendKey("//textarea[@placeholder='输入描述']", "自动化测试描述");

        Reporter.log("编辑目标");
        auto.ui.sendKey("//textarea[@placeholder='设定清晰的目标']", "自动化测试目标");

        Reporter.log("编辑成果");
        auto.ui.sendKey("//textarea[@placeholder='记录实际成果']", "自动化测试成果");
        auto.ui.click("//label[text()='实际成果']");

        Reporter.log("验证列表数据");
        auto.ui.click("//div[text()='返回列表']");
        auto.function.searchFlagByName("自动化FLAG基本信息编辑测试-勿删");
        auto.assertion.isElementExist("//div[text()='自动化测试描述']");
        auto.assertion.isElementExist("//div[contains(text(),'非常重要')]");
        auto.assertion.isElementExist("//div[text()='自动化测试目标']");

    }

}
