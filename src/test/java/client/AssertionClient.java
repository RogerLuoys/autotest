package client;

import commonClient.AssertionCommonClient;
import org.openqa.selenium.WebDriver;

/**
 * 自定义断言
 * 也可以继承Assert类
 */
public class AssertionClient extends AssertionCommonClient {


    public AssertionClient() {
        super();
    }

    public AssertionClient (UiClient ui) {
        super(ui);
    }

}
