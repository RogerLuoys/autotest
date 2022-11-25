package client;

import commonClient.AssertionCommonClient;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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

    /**
     * 判断是否为真
     * @param result true断言成功，非true断言失败
     */
    public void isTrue(String result) {
        if (result.equalsIgnoreCase("true")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    /**
     * 判断是否为假
     * @param result false断言成功，非false断言失败
     */
    public void isFalse(String result) {
        if (result.equalsIgnoreCase("false")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

}
