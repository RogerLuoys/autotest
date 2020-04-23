package hello;

import connect.HttpClient;
import org.junit.Test;


public class helloTest {

    @Test
    public void Test1() {
        HttpClient http1 = new HttpClient();
//        String a = http1.hello();
//        String b = http1.doGet("http://118.24.117.181:8888/hello");
        String b = http1.doGet("http://yqdz.manage-test.sit.91lyd.com/xqy-portal-web/manage/v2/whitelist/isInCustomWhitelist?code=manage_5_0_White&_=1587537844654");
        System.out.print(b);
    }

}
