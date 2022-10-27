package client;

import commonClient.UiClient;
import config.Config;

/**
 * 相当于base page
 * 需要在UI相关套件执行前实例化内部的webdriver，且套件结束后关闭资源
 */
public class CommonUi extends UiClient {

    // 这里可以加自定义方法
    @Override
    public void openUrl(String url) {
        if (url.toLowerCase().startsWith("http")) {
            super.openUrl(url);
        } else {
            super.openUrl(Config.UI_FLAG_URL + url);
        }
    }
}
