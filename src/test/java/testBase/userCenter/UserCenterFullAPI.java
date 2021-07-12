package testBase.userCenter;

import db.DB;
import api.HTTP;
import api.RPC;
import db.impl.DBJdbcTemplateImpl;
import api.impl.HttpClientImpl;
import api.impl.RPCDubboImpl;
import util.JsonUTIL;
import util.impl.JsonUTILImpl;

public class UserCenterFullAPI {
    public final UserCenterConfig config = new UserCenterConfig();
    public final RPC rpc = new RPCDubboImpl();
    public final JsonUTIL jsonUtil = new JsonUTILImpl();
    public final DB ucDB = new DBJdbcTemplateImpl(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");
}
