package autotest.ui.flag.flag;

import org.testng.Reporter;
import org.testng.annotations.Test;
import autotest.ui.UITestBase;

public class CycleTaskEditTest extends UITestBase {

    // 除一个@Test注解外，不能有别的Testng注解
//    @BeforeMethod
//    void prepareEnv() {
//        auto.flagDB.update("update task set task_name='自动化编辑任务测试-勿删', description=null, point=1, cycle='1' where task_id='216166577939354';");
//    }

    @Test(description = "编辑flag周期任务")
    void editTask() {
        // 数据还原
        auto.sql.flag("update task set task_name='自动化编辑任务测试-勿删', description=null, point=1, cycle='1' where task_id='216166577939354';");

        Reporter.log("打开详情");
        auto.function.openFlagDetail("自动化FLAG任务编辑测试-勿删");

        Reporter.log("进入编辑弹窗");
        auto.ui.click("//span[text()='编辑']");

        Reporter.log("编辑并保存");
        auto.ui.sendKeyWithClear("//input[@placeholder='请输入任务名']", "自动化编辑任务名");
        auto.ui.click("//span[@class='el-input-number__increase']");
        auto.ui.click("//span[contains(text(), '周二')]");
        auto.ui.sendKey("//textarea[@placeholder='请描述你的任务']", "自动化编辑任务描述");
        auto.ui.click("//span[text()='保存']");
        auto.util.sleep(3);

        Reporter.log("验证编辑结果");
        auto.assertion.isElementExist("//div[text()='自动化编辑任务名']");//验证名称编辑结果
        auto.assertion.isElementExist("//span[contains(text(), '周二')]");//验证周期编辑结果
        auto.assertion.isElementExist("//div[text()='自动化编辑任务描述']");//验证描述编辑结果
        auto.assertion.isElementExist("//div[text()='2']");//验证积分编辑结果

    }
}
