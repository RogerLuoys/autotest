package supperTestClass;

import api.DB;
import api.UI;
import api.impl.DBJdbcTemplateImpl;
import api.impl.SeleniumImpl;

public class UIFullAPI {
    public final UI ui = new SeleniumImpl();
    public final DB db = new DBJdbcTemplateImpl("com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");
    public final Task task = new Task();


    public class Task {
        public void login(String userName, String password) {
            ui.sendKey("//input[@placeholder='请输入账号']", userName);
            ui.sendKey("//input[@placeholder='请输入密码']", password);
            ui.click("//span[text()='登录']");
            ui.forceWait(5L);
        }
    }
}

