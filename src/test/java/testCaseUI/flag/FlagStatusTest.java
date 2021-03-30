package testCaseUI.flag;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.ui.UITestBase;

public class FlagStatusTest extends UITestBase {

    @BeforeMethod
    void prepareEnv() {
        auto.flagDB.update("update flag set is_delete=0 where flag_id='116164847059855';");
        auto.flagDB.update("update flag_bind set is_delete=0 where flag_id='116164847059855';");
    }

    @Test
    void startFlag() {
        //数据准备
        auto.flagDB.update("update flag set status=1 where flag_id='116164847059855';");
        //按名称搜索
        auto.ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", "自动化FLAG状态测试-勿删");
        auto.ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
        auto.ui.forceWait(2L);
        //点编辑
        auto.ui.click(auto.ui.getElements(By.xpath("//div[@id='pane-flag']//span[text()='编辑']")).get(1));
        auto.ui.forceWait(2L);
        //更改状态为进行中
        auto.ui.click("//span[text()='开始']");
        auto.ui.click(auto.ui.getElements(By.xpath("//div[@class='el-popconfirm']//span[contains(text(),'确定')]")).get(1));
        //校验更改结果
        Assert.assertTrue(auto.ui.isElementExist("//span[text()='进行中']"), "判断标签是否更改");
    }

    @Test
    void completeFlag() {
        //数据准备
        auto.flagDB.update("update flag set status=2 where flag_id='116164847059855';");
        //按名称搜索
        auto.ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", "自动化FLAG状态测试-勿删");
        auto.ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
        auto.ui.forceWait(2L);
        //点编辑
        auto.ui.click(auto.ui.getElements(By.xpath("//div[@id='pane-flag']//span[text()='编辑']")).get(1));
        auto.ui.forceWait(2L);
        //更改状态为进行中
        auto.ui.click("//span[text()='完成']");
        auto.ui.click(auto.ui.getElements(By.xpath("//div[@class='el-popconfirm']//span[contains(text(),'确定')]")).get(1));
        //校验更改结果
        Assert.assertTrue(auto.ui.isElementExist("//span[text()='已完成']"), "判断标签是否更改");
    }

    @Test
    void deleteFlag() {
        //数据准备
        auto.flagDB.update("update flag set status=1 where flag_id='116164847059855';");
        //按名称搜索
        auto.ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", "自动化FLAG状态测试-勿删");
        auto.ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
        auto.ui.forceWait(2L);
        //点编辑
        auto.ui.click(auto.ui.getElements(By.xpath("//div[@id='pane-flag']//span[text()='编辑']")).get(1));
        auto.ui.forceWait(2L);
        //更改状态为进行中
        auto.ui.click("//span[text()='删除']");
        auto.ui.click(auto.ui.getElements(By.xpath("//div[@class='el-popconfirm']//span[contains(text(),'确定')]")).get(1));
        //校验更改结果
        Assert.assertFalse(auto.ui.isElementExist("//div[text()='自动化FLAG状态测试-勿删']"), "判断是否被删除");
    }

}
