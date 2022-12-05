package testCase.rpc.xdzb;

import common.FileUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import root.RpcDzbRoot;

public class DzbTestBase extends Config {

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
        String path = System.getProperty("user.dir") + "/" + this.getClass().getName().replace(".", "/") + ".xls";
        return FileUtil.getData(path);
    }
}
