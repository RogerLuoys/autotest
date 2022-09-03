package autotest;

import common.HttpClient;

/**
 * 发起请求时初始化资源，请求结束后关闭
 */
public class CommonHttp extends HttpClient {
    // 这里可以加自定义方法

    CommonConfig config = new CommonConfig();

    /**
     * 发起get请求
     * 如包含http协议头，则加上配置中的域名
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 请求结果
     */
    @Override
    public String get(String url) {
        if (url.toLowerCase().startsWith("http")) {
            return super.get(url);
        } else {
            return super.get(config.HTTP_FLAG_URL + url);
        }
    }

    @Override
    public String get(String url, String header) {
        if (url.toLowerCase().startsWith("http")) {
            return super.get(url, header);
        } else {
            return super.get(config.HTTP_FLAG_URL + url, header);
        }
    }

    @Override
    public String post(String url, String body) {
        if (url.toLowerCase().startsWith("http")) {
            return super.post(url, body);
        } else {
            return super.post(config.HTTP_FLAG_URL + url, body);
        }
    }
}
