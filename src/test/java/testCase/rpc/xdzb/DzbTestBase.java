package testCase.rpc.xdzb;

import common.FileUtil;
import commonTestng.Provider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import root.RpcDzbRoot;

public class DzbTestBase {

    /*---------------------------配置参数-------------------*/
    // 接口域名(调用环境)
    public static final String URL = "dubbo://localhost:20881/";

    public static final String ENV = "test2";
    /*---------------------------配置参数-------------------*/

    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static）
     */
    public static RpcDzbRoot auto;

    @BeforeSuite(alwaysRun = true)
    public void supperBeforeSuite() {
        auto = new RpcDzbRoot();
    }

    @BeforeClass
    public void beforeClass() {
        auto.rpc.setBaseURL(URL);
    }


    @DataProvider(name = "data")
    public Object[][] provider() {
        String path = System.getProperty("user.dir") + "\\src\\test\\java\\" + this.getClass().getName().replace(".", "\\") + ".xls";
        Object[][] data = Provider.getData(path);
        return data;
    }
}
