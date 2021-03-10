package supperTestClass;

import api.DB;
import api.HTTP;
import api.RPC;
import api.impl.DBJdbcTemplateImpl;
import api.impl.HttpClientImpl;
import api.impl.RPCDubboImpl;

public class UserCenterFullAPI {
    public final RPC rpc = new RPCDubboImpl();
    public final HTTP http = new HttpClientImpl();
    public final DB ucDB = new DBJdbcTemplateImpl(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");
}
