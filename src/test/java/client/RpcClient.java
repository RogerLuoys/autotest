package client;

import commonClient.RpcCommonClient;

/**
 * 请求时才初始化资源，结束后关闭
 */
public class RpcClient extends RpcCommonClient {
    // 这里可以加自定义方法

    String baseURL;

    /**
     * 设置域名，即测试环境 (在超类中使用)
     *
     * @param baseURL 调用域名
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    @Override
    public String invoke(String fullLocation, String paramType, String paramValue) {
        if (fullLocation.toLowerCase().startsWith("dubbo")) {
            return super.invoke(fullLocation, paramType, paramValue);
        } else {
            return super.invoke(baseURL + fullLocation, paramType, paramValue);
        }
    }
}
