package config;

/**
 * 配置类
 */
public class Config {

    // flag ui 自动化专用
    // 主网站
    public static final String UI_FLAG_URL = "http://118.24.117.181/";
    // 登录名
    public static final String UI_FLAG_USERNAME = "UITester";
    // 登录密码
    public static final String UI_FLAG_PASSWORD = "123456";
    // webdriver启动参数
    public static final String[] DRIVER_OPTIONS = {"--kiosk", "--disable-dev-shm-usage", "window-size=1920*1080",
            "--no-sandbox", "url=data:,", "--start-maximized", "--disable-gpu", "--hide-scrollbars",
            "--headless", "disable-infobars"};


    // baidu ui 自动化专用
    // 主网站
    public static final String UI_BAIDU_URL = "https://www.baidu.com/";

    // jue jin ui 自动化专用
    public static final String UI_JUE_JIN_URL = "https://juejin.cn/";

    // flag http 自动化专用
    // 接口域名(调用环境)
    public static final String HTTP_FLAG_URL = "http://118.24.117.181:9002/";


    // express http 自动化专用
    // 接口域名(调用环境)
    public static final String HTTP_EXPRESS_URL = "http://www.kuaidi100.com/";


    // uc rpc 自动化专用
    // 接口域名(调用环境)
    public static final String RPC_UC_URL = "dubbo://118.24.117.181:20881/";
    //本地
//    public static final String RPC_UC_URL = "dubbo://10.201.10.183:20881/";


    //  公用
    // flag数据源
    public static final String DB_FLAG_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_FLAG_URL = "jdbc:mysql://118.24.117.181:3306/onepiece?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=Asia/Shanghai";
    public static final String DB_FLAG_USERNAME = "testerone";
    public static final String DB_FLAG_PASSWORD = "testerone";
    // dzb数据源
    public static final String DB_DZB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_DZB_URL = "jdbc:mysql:******";
    public static final String DB_DZB_USERNAME = "*****";
    public static final String DB_DZB_PASSWORD = "*****";

}
