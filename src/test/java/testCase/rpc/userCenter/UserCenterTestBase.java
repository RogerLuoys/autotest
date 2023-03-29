package testCase.rpc.userCenter;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import root.RpcUcRoot;

public class UserCenterTestBase {

    /*---------------------------配置参数-------------------*/
    // uc rpc 自动化专用
    // 接口域名(调用环境)
    public static final String URL = "dubbo://localhost:20881/";
    //本地
//    public static final String URL = "dubbo://10.201.10.183:20881/";
    /*---------------------------配置参数-------------------*/


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
