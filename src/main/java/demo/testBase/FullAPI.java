package demo.testBase;

import api.HTTP;
import api.RPC;
import api.impl.HttpClientImpl;
import api.impl.RPCDubboImpl;
import db.DB;
import db.impl.DBJdbcTemplateImpl;
import ui.UI;
import ui.impl.UISeleniumImpl;
import util.ExcelUTIL;
import util.JsonUTIL;
import util.impl.ExcelUTILHSSFImpl;
import util.impl.ExcelUTILXSSFImpl;
import util.impl.JsonUTILImpl;

public class FullAPI {
    public final RPC rpc = new RPCDubboImpl();
    public final HTTP http = new HttpClientImpl();
    public final UI ui = new UISeleniumImpl();
    public final Task task = new Task();
    public final DB db = new DBJdbcTemplateImpl();
    public final JsonUTIL jsonUtil = new JsonUTILImpl();
    public final ExcelUTIL excelUTILHSSF = new ExcelUTILHSSFImpl();
//    public final ExcelUTIL excelUTILXSSF = new ExcelUTILXSSFImpl();

    public class Task {
        public void login() {
            System.out.println("login");
        }
    }
}
