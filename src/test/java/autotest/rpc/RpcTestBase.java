package autotest.rpc;

import autotest.CommonProxy;
import org.testng.annotations.BeforeSuite;

public class RpcTestBase {

    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static）
     */
    public static CommonProxy auto;

    @BeforeSuite(alwaysRun = true)
    public void supperBeforeSuite() {
        auto = new CommonProxy();
    }
}
