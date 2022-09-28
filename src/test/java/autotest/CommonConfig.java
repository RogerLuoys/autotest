package autotest;

/**
 * 配置类
 * 为了保持队形，不用static
 */
public class CommonConfig {

    /**
     * flag网页主域名
     */
    public final String WEB_FLAG_URL = "http://118.24.117.181/";

    /**
     * http 应用 flag 的服务器域名
     */
    public final String HTTP_FLAG_URL = "http://118.24.117.181:9002/";

    /**
     * PRC 应用 user center 的服务器域名
     */
    public final String RPC_UC_URL = "dubbo://118.24.117.181:20881/";
    //本地
//    public static final String RPC_UC_URL = "dubbo://10.201.10.183:20881/";

    /**
     * flag数据源
     */
    public final String DB_FLAG_DRIVER = "com.mysql.cj.jdbc.Driver";
    public final String DB_FLAG_URL = "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai";
    public final String DB_FLAG_USERNAME = "testerone";
    public final String DB_FLAG_PASSWORD = "testerone";

    /**
     * dzb数据源
     */
    public final String DB_DZB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public final String DB_DZB_URL = "jdbc:mysql:******";
    public final String DB_DZB_USERNAME = "*****";
    public final String DB_DZB_PASSWORD = "*****";
}
