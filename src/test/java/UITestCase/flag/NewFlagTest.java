package UITestCase.flag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import supperTestClass.UITestBase;

public class NewFlagTest extends UITestBase {

    @BeforeClass
    void resetData() {
        auto.db.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增FLAG测试');");
        auto.db.delete("delete from flag where flag_name = '自动化新增FLAG测试');");
    }

    @BeforeMethod
    void prepareEnv() {
        auto.ui.refresh(URL);
        auto.ui.forceWait(3L);
    }

    @AfterMethod
    void resetEnv() {
        auto.ui.forceWait(3L);
    }

    @Test
    void newFlag() {
        auto.ui.click("//span[contains(string(), '新增FLAG')]");
        auto.ui.sendKey("//div[@class='el-dialog__body']//input[@placeholder='请输入名称']", "自动化新增FLAG测试");
        auto.ui.click("//span[text()='确 定']");
    }

    @Test
    void newHabit() {
        auto.ui.click("//div[text()='习惯养成']");
        auto.ui.click("//span[contains(string(), '新增习惯')]");
        auto.ui.sendKey("//div[@class='el-dialog__body']//input[@placeholder='请输入名称']", "自动化新增习惯测试");
        auto.ui.click(auto.ui.getElements(By.xpath("//span[text()='确 定']")).get(1));
    }
}
