package root;

import client.*;
import po.baidu.BaiduCommonPO;
import po.juejin.JueJinCommonPO;

public class UiJueJinRoot {

    // 传参小于等于3个，传参类型只用String，返回只用String

    public UiClient ui = new UiClient();
    public HttpClient http = new HttpClient();
    public RpcClient rpc = new RpcClient();
    public SqlClients sql = new SqlClients();
    public AssertionClient assertion = new AssertionClient(ui);
    public UtilClient util = new UtilClient();
    public JueJinCommonPO po = new JueJinCommonPO(ui);

}
