package api;

import java.util.Map;

public interface HTTP {

    /**
     * 执行http get请求
     *
     * @param url 完整的url地址-http://ip:port/path
     * @return 返回json格式
     */
    String doGet(String url);

    /**
     * 执行http get请求
     *
     * @param url    完整的url地址-http://ip:port/path
     * @param params Key必须是字符串，Value只能是基本数据类型的包装类型
     * @return 返回json格式
     */
    String doGet(String url, Map<String, ?> params);

    /**
     * 执行http get请求
     *
     * @param url    完整的url地址-http://ip:port/path
     * @param params Key必须是字符串，Value只能是基本数据类型的包装类型
     * @param header 请求头
     * @return 返回json格式
     */
    String doGet(String url, Map<String, ?> params, Map<String, String> header);

    /**
     * 执行http delete请求
     *
     * @param url 完整的url地址-http://ip:port/path
     * @return 返回json格式
     */
    String doDelete(String url);

    /**
     * 执行http delete请求
     *
     * @param url    完整的url地址-http://ip:port/path
     * @param params Key必须是字符串，Value只能是基本数据类型的包装类型
     * @return 返回json格式
     */
    String doDelete(String url, Map<String, ?> params);

    /**
     * 执行http delete请求
     *
     * @param url    完整的url地址-http://ip:port/path
     * @param params Key必须是字符串，Value只能是基本数据类型的包装类型
     * @param header 请求头
     * @return 返回json格式
     */
    String doDelete(String url, Map<String, ?> params, Map<String, String> header);

    /**
     * 执行http post请求
     *
     * @param url  完整的url地址-http://ip:port/path
     * @param data 接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @return 返回json格式
     */
    String doPost(String url, Object data);

    /**
     * 执行http post请求
     *
     * @param url    完整的url地址-http://ip:port/path
     * @param data   接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @param header 请求头
     * @return 返回json格式
     */
    String doPost(String url, Object data, Map<String, String> header);

    /**
     * 执行http post请求
     *
     * @param url    完整的url地址-http://ip:port/path
     * @param data   接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @param header 请求头
     * @param params url参数，key必须是字符串，value只能是基本数据类型的包装类型
     * @return
     */
    String doPost(String url, Object data, Map<String, String> header, Map<String, ?> params);


    /**
     * 执行http put请求
     *
     * @param url  完整的url地址-http://ip:port/path
     * @param data 接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @return 返回json格式
     */
    String doPut(String url, Object data);

    /**
     * 执行http put请求
     *
     * @param url    完整的url地址-http://ip:port/path
     * @param data   接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @param header 请求头
     * @return 返回json格式
     */
    String doPut(String url, Object data, Map<String, String> header);

    /**
     * 执行http put请求
     *
     * @param url    完整的url地址-http://ip:port/path
     * @param data   接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @param header 请求头
     * @param params url参数，key必须是字符串，value只能是基本数据类型的包装类型
     * @return
     */
    String doPut(String url, Object data, Map<String, String> header, Map<String, ?> params);

}
