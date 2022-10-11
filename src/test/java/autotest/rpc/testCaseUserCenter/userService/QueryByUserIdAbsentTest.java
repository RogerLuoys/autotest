package autotest.rpc.testCaseUserCenter.userService;

import autotest.rpc.testCaseUserCenter.UserCenterTestBase;
import org.testng.annotations.Test;

public class QueryByUserIdAbsentTest extends UserCenterTestBase {

    @Test
    public void test() {
        String result = auto.rpc.invoke("com.luoys.upgrade.uc.share.service.UserService#queryByUserId", "java.lang.String", "10000");
        String message = auto.util.getFirstValue("message", result);
        auto.assertion.isEquals(message, "业务异常");
    }

}
