package testCase.ui.juejin;

public class Config {

    // jue jin ui 自动化专用
    // 环境域名
    public static final String URL = "https://juejin.cn/";
    // 启动参数
    public static final String[] OPTIONS = {"--disable-dev-shm-usage",
            "--no-sandbox", "url=data:,", "--start-maximized", "--disable-gpu", "--hide-scrollbars",
            "disable-infobars", "deviceName,Samsung Galaxy S8+","--headless"};

}
