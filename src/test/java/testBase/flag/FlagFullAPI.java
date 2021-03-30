package testBase.flag;

import db.DB;
import api.HTTP;
import api.RPC;
import db.impl.DBJdbcTemplateImpl;
import api.impl.HttpClientImpl;
import api.impl.RPCDubboImpl;

public class FlagFullAPI {
    public final RPC rpc = new RPCDubboImpl();
    public final HTTP http = new HttpClientImpl();
    public final DB flagDB = new DBJdbcTemplateImpl(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");

}
