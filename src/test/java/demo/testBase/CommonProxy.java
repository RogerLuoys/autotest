package demo.testBase;

import api.HTTPHttpClient;
import api.RPCDubbo;
import db.DBJdbcTemplate;
import ui.UISelenium;
import util.impl.ExcelUtil;
import util.impl.JsonUtil;
import util.impl.TimeUtil;

/**
 * 公共代理类
 */
public class CommonProxy {

    public final RPCDubbo rpc = new RPCDubbo();
    public final HTTPHttpClient http = new HTTPHttpClient();
    public final UISelenium ui = new UISelenium();
    public final DBJdbcTemplate db = new DBJdbcTemplate();
    public final JsonUtil jsonUtil = new JsonUtil();
    public final ExcelUtil excelUtil = new ExcelUtil();
    public final TimeUtil timeUtil = new TimeUtil();
    public final Task task = new Task();
    public final Config config = new Config();


    public class Task {
        public void login() {
            System.out.println("login");
        }
    }
}
