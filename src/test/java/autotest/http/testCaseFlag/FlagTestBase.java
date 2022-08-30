package autotest.http.testCaseFlag;

import org.testng.annotations.BeforeClass;

/**
 * 该测试专用账号/密码：APITester/123456
 * 账号userId： 416176799148282
 */
public class FlagTestBase {

    public FlagCommonProxy auto;

    // 用例执行时再开始实例化auto变量，因为用mvn test执行会先把test包下所有类实例化一遍
    @BeforeClass(description = "变量实例化")
    public void supperBeforeClass() {
        auto = new FlagCommonProxy();
    }

}
