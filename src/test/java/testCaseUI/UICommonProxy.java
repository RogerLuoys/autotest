package testCaseUI;

import db.DBJdbcTemplate;
import ui.UISelenium;

public class UICommonProxy {

    public final UIConfig config = new UIConfig();
    public final UISelenium ui = new UISelenium();
    public final DBJdbcTemplate flagDB = new DBJdbcTemplate(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");
    public final Task task = new Task(ui);

}
