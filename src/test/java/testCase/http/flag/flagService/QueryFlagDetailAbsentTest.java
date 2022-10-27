package testCase.http.flag.flagService;

import testCase.http.flag.FlagTestBase;
import org.testng.annotations.Test;

public class QueryFlagDetailAbsentTest extends FlagTestBase {

    @Test(description = "查询不存在的flag详情")
    void test() {
        // 调用查询接口
        String result = auto.http.get("flag/queryFlagDetail?flagId=123123");

        // 验证返回值
        String message = auto.util.getFirstValue(result, "message");
        auto.assertion.isEquals(message, "未查询到此flag");
    }

}
