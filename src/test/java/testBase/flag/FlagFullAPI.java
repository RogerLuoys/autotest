package testBase.flag;

import db.DB;
import api.HTTP;
import api.RPC;
import db.impl.DBJdbcTemplateImpl;
import api.impl.HttpClientImpl;
import api.impl.RPCDubboImpl;
import util.JsonUTIL;
import util.TimeUTIL;
import util.impl.JsonUTILImpl;
import util.impl.TimeUTILImpl;

public class FlagFullAPI {
    public final HTTP http = new HttpClientImpl();
    public final JsonUTIL jsonUtil = new JsonUTILImpl();
    public final TimeUTIL timeUtil = new TimeUTILImpl();
    public final DB flagDB = new DBJdbcTemplateImpl(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");

}
