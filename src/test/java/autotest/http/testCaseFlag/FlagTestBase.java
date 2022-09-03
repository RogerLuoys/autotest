package autotest.http.testCaseFlag;

import autotest.http.HttpTestBase;
import org.testng.annotations.BeforeClass;

/**
 * 该测试专用账号/密码：APITester/123456
 * 账号userId： 416176799148282
 */
public class FlagTestBase extends HttpTestBase {

    @BeforeClass
    public void beforeClass() {
        // 指定环境
        auto.http.setBaseURL(auto.config.HTTP_FLAG_URL);
    }
}
