package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 单例
 */
// 主要解决AssertionClient和UIClient要且必须要使用同一个webdriver的问题
public class AutomationBase {
    // UiClient和AssertionClient使用
    private static WebDriver driver = null;
    // DbClient 使用
    private static JdbcTemplate jdbcTemplate = null;

    private static void killLinuxProcess(String processName) {
    }

    private static void killWindowsProcess(String processName) {

    }


    public static WebDriver getChromeDriver() {
        if (driver == null) {
            // 设置启动参数
            ChromeOptions chromeOptions = new ChromeOptions();
            if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                killLinuxProcess("chromedriver");
                chromeOptions.addArguments("--kiosk");
                chromeOptions.addArguments("--disable-dev-shm-usage");
            } else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                killWindowsProcess("chromedriver");
            }
//            chromeOptions.addArguments("window-size=1920*1080");
            // 解决DevToolsActivePort文件不存在的报错
            chromeOptions.addArguments("--no-sandbox");
            // 设置启动浏览器空白页
            chromeOptions.addArguments("url=data:,");
            // 最大化
            chromeOptions.addArguments("--start-maximized");
            // 谷歌禁用GPU加速
            chromeOptions.addArguments("--disable-gpu");
            // 隐藏滚动条
            chromeOptions.addArguments("--hide-scrollbars");
            // 后台运行
//            chromeOptions.addArguments("--headless");
            // 去掉Chrome提示受到自动软件控制
            chromeOptions.addArguments("disable-infobars");
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

}
