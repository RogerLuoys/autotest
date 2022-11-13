package proxy;

import client.*;
import po.juejin.JueJinCommonPO;
import po.reader.ReaderCommonPO;

public class UiReaderProxy {

    // 传参小于等于3个，传参类型只用String或Integer，返回只用String

    public CommonUiAppium ui = new CommonUiAppium();
    public CommonHttp http = new CommonHttp();
    public CommonRpc rpc = new CommonRpc();
    public CommonSql sql = new CommonSql();
    public CommonAssertion assertion = new CommonAssertion();
    public ReaderCommonPO po = new ReaderCommonPO(ui);
    public CommonUtil util = new CommonUtil();

}
