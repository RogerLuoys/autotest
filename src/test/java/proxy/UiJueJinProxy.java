package proxy;

import client.*;
import po.baidu.BaiduCommonPO;
import po.juejin.JueJinCommonPO;

public class UiJueJinProxy {

    // 传参小于等于3个，传参类型只用String或Integer，返回只用String

    public CommonUi ui = new CommonUi();
    public CommonHttp http = new CommonHttp();
    public CommonRpc rpc = new CommonRpc();
    public CommonSql sql = new CommonSql();
    public CommonAssertion assertion = new CommonAssertion(ui);
    public JueJinCommonPO po = new JueJinCommonPO(ui);
    public CommonUtil util = new CommonUtil();

}
