package UITestCase.flag;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import supperTestClass.ui.UITestBase;

public class NewFlagTest extends UITestBase {

    @BeforeClass
    void resetData() {
        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增FLAG测试');");
        auto.flagDB.delete("delete from flag where flag_name = '自动化新增FLAG测试';");
        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增习惯测试');");
        auto.flagDB.delete("delete from flag where flag_name = '自动化新增习惯测试';");
    }

    @BeforeMethod
    void prepareEnv() {
        auto.ui.refresh(URL);
    }

    @AfterMethod
    void resetEnv() {
        auto.ui.forceWait(3L);
    }

    @Test
    void newFlag() {
        //点新增按钮
        auto.ui.click("//span[contains(text(), '新增FLAG')]");
        //输入名称，新增flag
        auto.ui.sendKey("//div[@class='el-dialog__body']//input[@placeholder='请输入名称']", "自动化新增FLAG测试");
        auto.ui.click("//span[text()='确 定']");
        auto.ui.forceWait(3L);
        //验证新增的flag是否存在
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化新增FLAG测试']"), "验证新增是否成功");
    }

    @Test
    void newHabit() {
        //点新增按钮
        auto.ui.click("//div[text()='习惯养成']");
        //输入名称，新增习惯
        auto.ui.click("//span[contains(text(), '新增习惯')]");
        auto.ui.sendKey("//div[@class='el-dialog__body']//input[@placeholder='请输入名称']", "自动化新增习惯测试");
        auto.ui.click(auto.ui.getElements(By.xpath("//span[text()='确 定']")).get(1));
        //验证习惯是否存在
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化新增习惯测试']"), "验证新增是否成功");
    }
}
