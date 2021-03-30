package testCaseUI.flag;

import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.ui.UITestBase;

public class FlagEditTest extends UITestBase {

    @Test
    void editBasic() {
        //还原数据
        auto.flagDB.update("update flag set description=null, expected=null, actual=null, priority=1 where flag_id='116165510085692';");

        //打开详情
        auto.task.openFlagDetail("自动化FLAG基本信息编辑测试-勿删");
        //编辑优先级
        auto.ui.click("//input[@placeholder='选择优先级']");
        auto.ui.click("//span[text()='非常重要']");
        //编辑描述
        auto.ui.sendKey("//textarea[@placeholder='输入描述']", "自动化测试描述");
        //编辑目标
        auto.ui.sendKey("//textarea[@placeholder='设定清晰的目标']", "自动化测试目标");
        //编辑成果
        auto.ui.sendKey("//textarea[@placeholder='记录实际成果']", "自动化测试成果");
        auto.ui.click("//label[text()='实际成果']");
        //验证列表数据
        auto.ui.click("//div[text()='返回列表']");
        auto.task.searchFlagByName("自动化FLAG基本信息编辑测试-勿删");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化测试描述']"), "验证描述编辑结果");
        Assert.assertTrue(auto.ui.isElementExist("//div[contains(text(),'非常重要')]"), "验证优先级编辑结果");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='自动化测试目标']"), "验证目标编辑结果");
    }

    @Test
    void editWitness(){
        //还原数据
        auto.flagDB.update("update flag_bind set witness_id=null, witness_name=null where flag_id='116166511224083';");

        //打开详情
        auto.task.openFlagDetail("自动化FLAG见证人编辑测试-勿删");
        //编辑见证人
        auto.ui.click("//span[text()='+ 添加见证人']");
        auto.ui.sendKey("//input[@placeholder='请输入用户编号']", "233");
        auto.ui.click("//label[text()='见证人']");
        //验证列表数据
        auto.ui.click("//div[text()='返回列表']");
        auto.task.searchFlagByName("自动化FLAG见证人编辑测试-勿删");
        Assert.assertTrue(auto.ui.isElementExist("//div[text()='罗永胜']"), "验证见证人编辑结果");
    }

}
