package autotest;


public class CommonProxy {

    public CommonConfig config = new CommonConfig();
    public CommonUi ui = new CommonUi();
    public CommonHttp http = new CommonHttp();
    public CommonRpc rpc = new CommonRpc();
    public CommonSql sql = new CommonSql();
    public CommonAssertion assertion = new CommonAssertion(ui);
    public CommonFunction function = new CommonFunction(ui);

}
