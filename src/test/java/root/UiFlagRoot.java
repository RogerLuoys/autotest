package root;

import client.*;
import po.flag.FlagCommonPO;

/**
 * 各种资源使用到时才初始化
 * 此类可以实例化多个
 */
public class UiFlagRoot {

    // 传参小于等于3个，传参类型只用String或Integer，返回只用String

    public UiClient ui = new UiClient();
    public HttpClient http = new HttpClient();
    public RpcClient rpc = new RpcClient();
    public SqlClients sql = new SqlClients();
    public AssertionClient assertion = new AssertionClient(ui);
    public UtilClient util = new UtilClient();
    public FlagCommonPO po = new FlagCommonPO(ui);

}
