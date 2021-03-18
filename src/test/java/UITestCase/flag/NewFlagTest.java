package UITestCase.flag;

import org.testng.annotations.*;
import supperTestClass.UITestBase;

public class NewFlagTest extends UITestBase {

    @BeforeMethod
    void prepareEnv() {
        auto.ui.refresh(URL);
        auto.ui.forceWait(3L);
    }


    @AfterMethod
    void resetEnv() {
        auto.ui.forceWait(10L);
    }

    @Test
    void newFlag() {
        auto.ui.clickByXpath("//span[text()='新增FLAG']");
        auto.ui.sendKeyByXpath("//input[@placeholder='请输入名称']", "自动化新增FLAG测试");
    }

    @Test
    void newHabit() {
        auto.ui.clickByXpath("//div[text()='习惯养成']");
        auto.ui.clickByXpath("//span[text()='新增习惯']");
        auto.ui.sendKeyByXpath("//input[@placeholder='请输入名称']", "自动化新增FLAG测试");
    }
}
