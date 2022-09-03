package autotest.ui.flag.flag;

import org.testng.Reporter;
import org.testng.annotations.*;
import autotest.ui.UITestBase;

public class NewFlagTest extends UITestBase {

//    @BeforeClass
//    void resetData() {
//        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增FLAG测试');");
//        auto.flagDB.delete("delete from flag where flag_name = '自动化新增FLAG测试';");
//
//    }

    @Test(description = "新增flag")
    void newFlag() {
        auto.sql.flag("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增FLAG测试');");
        auto.sql.flag("delete from flag where flag_name = '自动化新增FLAG测试';");

        Reporter.log("点新增按钮");
        auto.ui.click("//span[contains(text(), '新增FLAG')]");

        Reporter.log("输入名称新增flag");
        auto.ui.sendKey("//div[@class='el-dialog__body']//input[@placeholder='请输入名称']", "自动化新增FLAG测试");
        auto.ui.click("//span[text()='确 定']");

        Reporter.log("验证新增的flag是否存在");
        auto.function.searchFlagByName("自动化新增FLAG测试");
        auto.util.sleep(3);
        auto.assertion.isElementExist("//div[text()='自动化新增FLAG测试']");
    }

}
