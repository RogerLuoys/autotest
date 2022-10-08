package autotest.http.express;

import autotest.http.HttpTestBase;
import org.testng.annotations.BeforeClass;

public class ExpressTestBase extends HttpTestBase {

    @BeforeClass
    public void beforeClass() {
        // 指定环境
        auto.http.setBaseURL(auto.config.HTTP_EXPRESS_URL);
    }

}
