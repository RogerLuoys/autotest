package root;

import client.AssertionClient;
import client.HttpClient;
import client.SqlClients;
import client.UtilClient;

/**
 * 各种资源使用到时才初始化
 * 此类可以实例化多个
 */
public class HttpExpressRoot {

    // 传参小于等于3个，传参类型只用String，返回只用String

    public HttpClient http = new HttpClient();
    public SqlClients sql = new SqlClients();
    public AssertionClient assertion = new AssertionClient();
    public UtilClient util = new UtilClient();

}
