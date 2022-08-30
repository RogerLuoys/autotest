package autotest;

import common.UiClient;
import org.openqa.selenium.By;

public class CommonFunction {

    private UiClient ui;

    public CommonFunction(UiClient ui) {
        this.ui = ui;
    }

    public void loginFlag(String userName, String password) {
        //输入账号和名称并登录
        ui.sendKey("//input[@placeholder='请输入账号']", userName);
        ui.sendKey("//input[@placeholder='请输入密码']", password);
        ui.click("//span[text()='登录']");
        ui.forceWait(3);
    }

    public void openFlagDetail(String flagName) {
        //按名称搜索
        ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", flagName);
        ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
        ui.forceWait(2);
        //点编辑
        ui.click(ui.getElements(By.xpath("//div[@id='pane-flag']//span[text()='编辑']")).get(1));
        ui.forceWait(2);
    }

    public void searchFlagByName(String flagName) {
        //按名称搜索
        ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", flagName);
        ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
        ui.forceWait(2);
    }
}
