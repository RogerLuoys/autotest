package proxy;

import common.*;

/**
 * 各种资源使用到时才初始化
 * 此类可以实例化多个
 */
public class RpcDzbProxy {

    // 传参小于等于3个，传参类型只用String或Integer，返回只用String

    public CommonRpc rpc = new CommonRpc();
    public CommonSql sql = new CommonSql();
    public CommonAssertion assertion = new CommonAssertion();
    public CommonUtil util = new CommonUtil();

}
