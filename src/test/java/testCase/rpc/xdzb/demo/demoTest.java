package testCase.rpc.xdzb.demo;

import org.testng.annotations.Test;
import testCase.rpc.xdzb.DzbTestBase;

public class demoTest extends DzbTestBase {

//    @Test
    public void test() {
        String str1 = auto.sql.dzb("select * from dzb****");
        System.out.println(str1);
    }
}
