package testCase.rpc.xdzb;

import org.testng.annotations.BeforeSuite;
import root.RpcDzbRoot;

public class DzbTestBase {

    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static）
     */
    public static RpcDzbRoot auto;

    @BeforeSuite(alwaysRun = true)
    public void supperBeforeSuite() {
        auto = new RpcDzbRoot();
    }

}
