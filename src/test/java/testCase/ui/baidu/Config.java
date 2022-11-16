package testCase.ui.baidu;

public class Config {

    // baidu ui 自动化专用
    // 环境域名
    public static final String URL = "https://www.baidu.com/";
    // webdriver启动参数,"--headless"
    public static final String[] OPTIONS = {"--disable-dev-shm-usage",
            "--no-sandbox", "url=data:,", "--start-maximized", "--disable-gpu", "--hide-scrollbars",
            "disable-infobars","--headless"};

}
