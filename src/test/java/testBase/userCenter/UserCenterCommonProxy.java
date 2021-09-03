package testBase.userCenter;

import api.RPCDubbo;
import db.DBJdbcTemplate;
import util.JsonUtil;

public class UserCenterCommonProxy {
    public final UserCenterConfig config = new UserCenterConfig();
    public final RPCDubbo rpc = new RPCDubbo();
    public final JsonUtil jsonUtil = new JsonUtil();
    public final DBJdbcTemplate ucDB = new DBJdbcTemplate(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");
}
