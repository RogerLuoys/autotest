package client;

import commonClient.HttpCommonClient;

/**
 * 发起请求时初始化资源，请求结束后关闭
 */
public class HttpClient extends HttpCommonClient {
    // 这里可以加自定义方法

//    private String baseURL;
//
//    /**
//     * 设置域名，即测试环境 (在超类中使用)
//     *
//     * @param baseURL 调用域名
//     */
//    public void setBaseURL(String baseURL) {
//        this.baseURL = baseURL;
//    }
//
//    /**
//     * 发起get请求
//     * 如包含http协议头，则加上配置中的域名
//     *
//     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
//     * @return 请求结果
//     */
//    @Override
//    public String get(String url) {
//        if (url.toLowerCase().startsWith("http")) {
//            return super.get(url);
//        } else {
//            return super.get(baseURL + url);
//        }
//    }
//
//    @Override
//    public String get(String url, String header) {
//        if (url.toLowerCase().startsWith("http")) {
//            return super.get(url, header);
//        } else {
//            return super.get(baseURL + url, header);
//        }
//    }
//
//    @Override
//    public String post(String url, String body) {
//        if (url.toLowerCase().startsWith("http")) {
//            return super.post(url, body);
//        } else {
//            return super.post(baseURL + url, body);
//        }
//    }
//
//    @Override
//    public String put(String url) {
//        if (url.toLowerCase().startsWith("http")) {
//            return super.put(url);
//        } else {
//            return super.put(baseURL + url);
//        }
//    }
//
//    @Override
//    public String delete(String url) {
//        if (url.toLowerCase().startsWith("http")) {
//            return super.delete(url);
//        } else {
//            return super.delete(baseURL + url);
//        }
//    }
}
