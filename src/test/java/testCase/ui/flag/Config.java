package testCase.ui.flag;

public class Config {

    // flag ui 自动化专用
    // 环境域名
    public static final String URL = "http://118.24.117.181/";
    // 登录名
    public static final String USERNAME = "UITester";
    // 登录密码
    public static final String PASSWORD = "123456";
    // webdriver启动参数,"--headless"
    public static final String[] OPTIONS = {"--disable-dev-shm-usage",
            "--no-sandbox", "url=data:,", "--start-maximized", "--disable-gpu", "--hide-scrollbars",
            "disable-infobars","--headless"};


}
