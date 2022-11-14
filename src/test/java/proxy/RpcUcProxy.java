package proxy;

import client.AssertionClient;
import client.RpcClient;
import client.SqlClients;
import client.UtilClient;

/**
 * 各种资源使用到时才初始化
 * 此类可以实例化多个
 */
public class RpcUcProxy {

    // 传参小于等于3个，传参类型只用String或Integer，返回只用String

    public RpcClient rpc = new RpcClient();
    public SqlClients sql = new SqlClients();
    public AssertionClient assertion = new AssertionClient();
    public UtilClient util = new UtilClient();

}
