package testCase.rpc.xdzb.demo;

import org.testng.annotations.Test;
import testCase.rpc.xdzb.DzbTestBase;

public class demoTest2 extends DzbTestBase {

    @Test(dataProvider = "data")
    public void test(String str1, String str2, String str3, String str4) {
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        auto.assertion.isTrue("true");
    }
}
