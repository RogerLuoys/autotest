package testCaseUserCenter;

import org.testng.annotations.BeforeMethod;

public class UserCenterTestBase {
    //云服务器
//    public static final String URL = "dubbo://118.24.117.181:20881/";
    //本地
//    public static final String URL = "dubbo://10.201.10.183:20881/";

    public static final UserCenterCommonProxy auto = new UserCenterCommonProxy();


    @BeforeMethod
    public void supperBeforeMethod() {
        auto.rpc.forceWait(1);
    }


}