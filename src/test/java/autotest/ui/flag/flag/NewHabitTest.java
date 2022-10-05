package autotest.ui.flag.flag;

import autotest.ui.flag.FlagTestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class NewHabitTest extends FlagTestBase {

    @Test(description = "新增习惯")
    void newHabit() {
        auto.sql.flag("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增习惯测试');");
        auto.sql.flag("delete from flag where flag_name = '自动化新增习惯测试';");

        Reporter.log("点新增按钮");
        auto.ui.click("//div[text()='习惯养成']");

        Reporter.log("输入名称新增习惯");
        auto.ui.click("//span[contains(text(), '新增习惯')]");
        auto.ui.sendKey("//div[@class='el-dialog__body']//input[@placeholder='请输入名称']", "自动化新增习惯测试");
        auto.ui.click("//span[text()='确 定']", 1);

        Reporter.log("验证习惯是否存在");
//        auto.function.searchFlagByName("自动化新增习惯测试");
        //按名称搜索
        auto.ui.sendKey("//div[@id='pane-habit']//input[@placeholder='请输入名称']", "自动化新增习惯测试");
        auto.ui.click("//div[@id='pane-habit']//i[@class='el-icon-search']");
        auto.util.sleep(3);
        auto.assertion.isElementExist("//div[text()='自动化新增习惯测试']");
    }
}
