package autotest.rpc.testCaseUserCenter.userService;

import autotest.rpc.testCaseUserCenter.UserCenterTestBase;
import org.testng.annotations.Test;

public class QueryByUserIdAbsentTest extends UserCenterTestBase {

    @Test(description = "查询不存在的用户")
    public void test() {
        // 调查询接口
        String result = auto.rpc.invoke("com.luoys.upgrade.uc.share.service.UserService#queryByUserId", "java.lang.String", "10000");

        // 校验结果
        String message = auto.util.getFirstValue("message", result);
        auto.assertion.isEquals(message, "业务异常");
    }

}
