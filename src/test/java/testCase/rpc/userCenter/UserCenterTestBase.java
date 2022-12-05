package testCase.rpc.userCenter;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import root.RpcUcRoot;

public class UserCenterTestBase extends Config {
    //云服务器
//    public static final String URL = "dubbo://118.24.117.181:20881/";
    //本地
//    public static final String URL = "dubbo://10.201.10.183:20881/";


    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static）
     */
    public static RpcUcRoot auto;

    @BeforeSuite(alwaysRun = true)
    public void supperBeforeSuite() {
        auto = new RpcUcRoot();
    }

    @BeforeClass
    public void beforeClass() {
        auto.rpc.setBaseURL(URL);
    }

}
