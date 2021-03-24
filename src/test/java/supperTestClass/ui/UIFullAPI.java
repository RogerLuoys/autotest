package supperTestClass.ui;

import api.DB;
import api.UI;
import api.impl.DBJdbcTemplateImpl;
import api.impl.SeleniumImpl;
import org.openqa.selenium.By;

public class UIFullAPI {
    public final UI ui = new SeleniumImpl();
    public final DB flagDB = new DBJdbcTemplateImpl(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");
    public final Task task = new Task();


    public class Task {
        public void login(String userName, String password) {
            //输入账号和名称并登录
            ui.sendKey("//input[@placeholder='请输入账号']", userName);
            ui.sendKey("//input[@placeholder='请输入密码']", password);
            ui.click("//span[text()='登录']");
            ui.forceWait(3L);
        }

        public void openFlagDetail(String flagName) {
            //按名称搜索
            ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", flagName);
            ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
            ui.forceWait(2L);
            //点编辑
            ui.click(ui.getElements(By.xpath("//div[@id='pane-flag']//span[text()='编辑']")).get(1));
            ui.forceWait(2L);
        }

        public void searchFlagByName(String flagName) {
            //按名称搜索
            ui.sendKey("//div[@id='pane-flag']//input[@placeholder='请输入名称']", flagName);
            ui.click("//div[@id='pane-flag']//i[@class='el-icon-search']");
            ui.forceWait(2L);
        }
    }
}

