package testCase.http.flag.flagBindService;

import testCase.http.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class QueryFlagListLastIndexTest extends FlagTestBase {

    @Test(description = "查看尾页")
    void test2() {
        Reporter.log("调用接口");
        String result = auto.http.post("flagBind/queryFlagList", "{\"pageIndex\":99,\"ownerId\":\"416176799148282\"}");

        Reporter.log("验证结果");
        String list = auto.util.getJsonAny("list", result);
        auto.assertion.isEquals(list, "[]");
    }

}
