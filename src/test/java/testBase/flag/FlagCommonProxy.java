package testBase.flag;

import api.HTTPHttpClient;
import db.DBJdbcTemplate;
import util.JsonUtil;
import util.TimeUtil;

public class FlagCommonProxy {

    public final FlagConfig config = new FlagConfig();
    public final HTTPHttpClient http = new HTTPHttpClient();
    public final JsonUtil jsonUtil = new JsonUtil();
    public final TimeUtil timeUtil = new TimeUtil();
    public final DBJdbcTemplate flagDB = new DBJdbcTemplate(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai",
            "testerone",
            "testerone");

}
