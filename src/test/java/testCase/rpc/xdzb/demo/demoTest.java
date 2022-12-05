package testCase.rpc.xdzb.demo;

import commonTestng.RetryAnalyzer;
import org.testng.annotations.Test;
import testCase.rpc.xdzb.DzbTestBase;

public class demoTest extends DzbTestBase {

//    @Test
    public void test() {
//        String str1 = auto.sql.dzb("select * from dzb****");
//        System.out.println(str1);
        String path = System.getProperty("user.dir") + "/" + this.getClass().getName().replace(".", "/") + ".xls";
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRetry() {
        auto.assertion.isFalse("true");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRetry2() {
        auto.assertion.isFalse("false");
    }

    @Test
    public void testRetry3() {
        auto.assertion.isFalse("true");
    }

}
