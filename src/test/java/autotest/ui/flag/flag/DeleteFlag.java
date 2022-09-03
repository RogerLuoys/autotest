package autotest.ui.flag.flag;

import autotest.ui.flag.FlagTestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DeleteFlag extends FlagTestBase {

    @Test
    void deleteFlag() {
        Reporter.log("数据准备");
        auto.sql.flag("update flag set is_delete=0 where flag_id='116164847059855';");
        auto.sql.flag("update flag_bind set is_delete=0 where flag_id='116164847059855';");
        auto.sql.flag("update flag set status=1 where flag_id='116164847059855';");

        Reporter.log("按名称搜索");
        auto.ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", "自动化FLAG状态测试-勿删");
        auto.ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
        auto.util.sleep(2);

        Reporter.log("点编辑");
        auto.ui.click("//div[@id='pane-flag']//span[text()='编辑']", 1);
        auto.util.sleep(2);

        Reporter.log("更改状态为进行中");
        auto.ui.click("//span[text()='删除']");
        auto.ui.click("//div[@class='el-popconfirm']//span[contains(text(),'确定')]", 1);

        Reporter.log("校验更改结果");
        auto.assertion.isElementExist("//div[text()='自动化FLAG状态测试-勿删']");

    }

}
