package testCaseUI.flag;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import testBase.ui.UITestBase;

public class NewFlagTest extends UITestBase {

    @BeforeClass
    void resetData() {
        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增FLAG测试');");
        auto.flagDB.delete("delete from flag where flag_name = '自动化新增FLAG测试';");
        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增习惯测试');");
        auto.flagDB.delete("delete from flag where flag_name = '自动化新增习惯测试';");
    }

    @Test(description = "新增flag")
    void newFlag() {
        Reporter.log("点新增按钮");
        auto.ui.click("//span[contains(text(), '新增FLAG')]");

        Reporter.log("输入名称新增flag");
        auto.ui.sendKey("//div[@class='el-dialog__body']//input[@placeholder='请输入名称']", "自动化新增FLAG测试");
        auto.ui.click("//span[text()='确 定']");

        Reporter.log("验证新增的flag是否存在");
        auto.task.searchFlagByName("自动化新增FLAG测试");
        auto.ui.forceWait(3);
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化新增FLAG测试']"), "验证新增是否成功");
    }

    @Test(description = "新增习惯")
    void newHabit() {
        Reporter.log("点新增按钮");
        auto.ui.click("//div[text()='习惯养成']");

        Reporter.log("输入名称新增习惯");
        auto.ui.click("//span[contains(text(), '新增习惯')]");
        auto.ui.sendKey("//div[@class='el-dialog__body']//input[@placeholder='请输入名称']", "自动化新增习惯测试");
        auto.ui.click(auto.ui.getElements(By.xpath("//span[text()='确 定']")).get(1));

        Reporter.log("验证习惯是否存在");
        auto.task.searchFlagByName("自动化新增习惯测试");
        auto.ui.forceWait(3);
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化新增习惯测试']"), "验证新增是否成功");
    }
}
