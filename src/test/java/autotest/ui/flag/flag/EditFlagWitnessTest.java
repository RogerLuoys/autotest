package autotest.ui.flag.flag;

import autotest.ui.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class EditFlagWitnessTest extends FlagTestBase {

    @Test(description = "编辑flag见证人")
    void editWitness(){
        Reporter.log("还原数据");
        auto.sql.flag("update flag_bind set witness_id=null, witness_name=null where flag_id='116166511224083';");

        Reporter.log("打开详情");
        auto.po.openFlagDetail("自动化FLAG见证人编辑测试-勿删");

        Reporter.log("编辑见证人");
        auto.ui.click("//span[text()='+ 添加见证人']");
        auto.ui.sendKey("//input[@placeholder='请输入用户编号']", "233");
        auto.ui.click("//label[text()='见证人']");

        Reporter.log("验证列表数据");
        auto.ui.click("//div[text()='返回列表']");
        auto.po.searchFlagByName("自动化FLAG见证人编辑测试-勿删");
        auto.assertion.isElementExist("//div[text()='罗永胜']");

    }

}
