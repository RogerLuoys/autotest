package autotest;

import common.UiClient;
import org.openqa.selenium.By;

/**
 * 相当于总的PO，与util有区别
 */
public class CommonFunction {

    private UiClient ui;

    public CommonFunction(UiClient ui) {
        this.ui = ui;
    }

    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    private void forceWait(int second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            System.out.println("\n---->线程睡眠异常");
        }
    }

    public void loginFlag(String userName, String password) {
        //输入账号和名称并登录
        ui.sendKey("//input[@placeholder='请输入账号']", userName);
        ui.sendKey("//input[@placeholder='请输入密码']", password);
        ui.click("//span[text()='登录']");
        forceWait(3);
    }

    public void openFlagDetail(String flagName) {
        //按名称搜索
        ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", flagName);
        ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
        forceWait(2);
        //点编辑
        ui.click("//div[@id='pane-flag']//span[text()='编辑']", 1);
        forceWait(2);
    }

    public void searchFlagByName(String flagName) {
        //按名称搜索
        ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", flagName);
        ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
        forceWait(2);
    }
}
