package commonClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//1 创建HttpClient客户端
//2 创建Http请求
//3 设置
//4 发送请求
public class HttpCommonClient {

    private final List<Header> DEFAULT_HEADER = new ArrayList<>();
    private CloseableHttpClient httpClient = null;
    private String defaultUrl = null;

    /**
     * 处理get请求参数，并转换成HttpGet对象
     * @param url 完整url(可带参数)
     * @param headers 请求头
     * @return HttpGet
     */
    private HttpGet transformGet(String url, String headers) {
        if (!url.startsWith("http")) {
            url = this.defaultUrl + url;
        }
        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头
        httpGet.setHeaders(this.transformHeaders(headers));
        return httpGet;
    }

    /**
     * 处理post请求参数，并转换成HttpGet对象
     * @param url 完整url(可带参数)
     * @param body 请求体
     * @param headers 请求头
     * @return HttpPost
     */
    private HttpPost transformPost(String url, String body, String headers) {
        if (!url.startsWith("http")) {
            url = this.defaultUrl + url;
        }
        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeaders(this.transformHeaders(headers));
        if (body != null) {
            //设置编码格式避免中文乱码
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
            stringEntity.setContentType("application/json");
            // 设置 HttpPost 参数
            httpPost.setEntity(stringEntity);
        }
        return httpPost;
    }

    /**
     * 处理delete请求参数，并转换成HttpDelete对象
     * @param url 完整url(可带参数)
     * @param headers 请求头
     * @return HttpDelete
     */
    private HttpDelete transformDelete(String url, String headers) {
        if (!url.startsWith("http")) {
            url = this.defaultUrl + url;
        }
        // 创建 HttpDelete 请求
        HttpDelete httpDelete = new HttpDelete(url);
        // 设置请求头
        httpDelete.setHeaders(this.transformHeaders(headers));
        return httpDelete;
    }

    /**
     * 处理put请求参数，并转换成HttpPut对象
     * @param url 完整url(可带参数)
     * @param body 请求体
     * @param headers 请求头
     * @return HttpPut
     */
    private HttpPut transformPut(String url, String body, String headers) {
        if (!url.startsWith("http")) {
            url = this.defaultUrl + url;
        }
        // 创建 HttpPut 请求
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeaders(this.transformHeaders(headers));
        if (body != null) {
            //设置编码格式避免中文乱码
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
            stringEntity.setContentType("application/json");
            // 设置 HttpPost 参数
            httpPut.setEntity(stringEntity);
        }
        return httpPut;
    }

    private Header[] transformHeaders(String headers) {
        List<Header> headerList = new ArrayList<>();
        headerList.add(new BasicHeader("Connection", "keep-alive"));
        headerList.addAll(DEFAULT_HEADER);
        if (headers == null) {
            return headerList.toArray(new Header[0]);
        }
        JSONObject jsonObject = JSON.parseObject(headers);
        for (String key : jsonObject.keySet()) {
            headerList.add(new BasicHeader(key, jsonObject.get(key).toString()));
        }
        return headerList.toArray(new Header[0]);
    }

    /**
     * 执行http请求
     * @param request 请求对象
     * @return 调用结果
     */
    private CloseableHttpResponse executeRequest(HttpUriRequest request) throws IOException {
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        return httpClient.execute(request);
    }

    /**
     * 执行请求并获取结果中的body值
     * @param request 请求
     * @return response body
     */
    private String httpBody(HttpUriRequest request) {
        try {
            CloseableHttpResponse httpResponse = this.executeRequest(request);
            String result = EntityUtils.toString(httpResponse.getEntity());
            httpResponse.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * 执行请求并获取结果中的header
     * @param request 请求
     * @return response body
     */
    private String httpHeader(HttpUriRequest request, String headerName) {
        try {
            CloseableHttpResponse httpResponse = this.executeRequest(request);
            Header[] header;
            if (headerName == null) {
                header = httpResponse.getAllHeaders();
            } else {
                header = httpResponse.getHeaders(headerName);
            }
            httpResponse.close();
            return JSON.toJSONString(header);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * 关闭客户端
     */
    public void close() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            System.out.println("===>关闭http客户端失败！");
        }
    }

    /**
     * 设置默认url(用于指定调用环境)
     * @param defaultUrl 带协议头的环境地址
     */
    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    /**
     * 设置默认请求头
     * @param headerName 请求头的名字
     * @param headerValue 请求头的值
     */
    public void setDefaultHeader(String headerName, String headerValue) {
        DEFAULT_HEADER.removeIf(header -> header.getName().equals(headerName));
        DEFAULT_HEADER.add(new BasicHeader(headerName, headerValue));
    }

    /**
     * 添加默认cookie，原默认也保留
     * @param cookieValue cookie值，如 "token=****; accountId=****"
     */
    public void addDefaultCookie(String cookieValue) {
        String cookies = null;
        for (Header header: DEFAULT_HEADER) {
            if (header.getName().equals("Cookie")) {
                cookies = header.getValue();
            }
        }
        cookies = cookies + "; " + cookieValue;
        DEFAULT_HEADER.add(new BasicHeader("Cookie", cookies));
    }

    /**
     * 设置默认请求头
     * @param cookieValue cookie值，如 "token=****; accountId=****"
     */
    public void setDefaultCookie(String cookieValue) {
        DEFAULT_HEADER.removeIf(header -> header.getName().equals("Cookie"));
        DEFAULT_HEADER.add(new BasicHeader("Cookie", cookieValue));
    }

    /**
     * 查询默认cookie中对应的值
     * @param cookieName cookie名
     * @return cookie值
     */
    public String queryDefaultCookie(String cookieName) {
        // cookies格式为 name1=value1; name2=value2
        String cookies = "";
        for (Header header: DEFAULT_HEADER) {
            if (header.getName().equals("Cookie")) {
                cookies = header.getValue();
            }
        }
        int startIndex = cookies.indexOf(cookieName) + cookieName.length() + 1;
        int endIndex = cookies.indexOf(";", startIndex);
        return cookies.substring(startIndex, endIndex);
    }

    /**
     * 执行http get请求
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public String get(String url) {
        HttpGet httpGet = this.transformGet(url, null);
        return this.httpBody(httpGet);
    }

    /**
     * 执行http get请求
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param header 请求头，需要json格式
     * @return 返回json格式
     */
    public String get(String url, String header) {
        HttpGet httpGet = this.transformGet(url, header);
        return this.httpBody(httpGet);
    }

    /**
     * 执行http get请求，并返回所有header
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public String getForHeader(String url) {
        HttpGet httpGet = this.transformGet(url, null);
        return this.httpHeader(httpGet, null);
    }

    /**
     * 执行http get请求，并返回指定header
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param headerName  指定获取的返回头
     * @return 返回json格式
     */
    public String getForHeader(String url, String headerName) {
        HttpGet httpGet = this.transformGet(url, null);
        return this.httpHeader(httpGet, headerName);
    }

    /**
     * 执行http get请求，并将返回的cookie设置为默认
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public void getAndCookie(String url) {
        this.setDefaultCookie(getForCookie(url, null));
    }

    /**
     * 执行http get请求，并返回所有cookie
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public String getForCookie(String url) {
        return this.getForCookie(url, null);
    }

    /**
     * 执行http get请求，并返回指定cookie
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param cookieName  指定获取的cookie名
     * @return 返回json格式
     */
    public String getForCookie(String url, String cookieName) {
        StringBuilder result = new StringBuilder();
        HttpGet httpGet = transformGet(url, null);
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        try {
            httpClient.execute(httpGet);
            List<Cookie> cookieList = cookieStore.getCookies();
            for (Cookie cookie: cookieList) {
                if (cookieName == null || cookieName.equals(cookie.getName())) {
                    result.append("; ").append(cookie.getName()).append("=").append(cookie.getValue());
                }
            }
            if (result.length() > 2) {
                result.delete(0, 2);
            }
            httpClient.close();
            cookieStore.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 执行http post请求
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public String post(String url) {
        HttpPost httpPost = this.transformPost(url, null, null);
        return this.httpBody(httpPost);
    }

    /**
     * 执行http post请求
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param body 接口传入的请求体，application/json格式
     * @return 返回json格式
     */
    public String post(String url, String body) {
        HttpPost httpPost = this.transformPost(url, body, null);
        return this.httpBody(httpPost);
    }

    /**
     * 执行http post请求
     *
     * @param url  完整的url地址-http://ip:port/path
     * @param body 接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @param header 请求头，json字符串
     * @return 返回json格式
     */
    public String post(String url, String body, String header) {
        HttpPost httpPost = this.transformPost(url, body, header);
        return this.httpBody(httpPost);
    }

    /**
     * 执行http post请求，返回header
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param body 接口传入的请求体，application/json格式
     * @return 返回json格式
     */
    public String postForHeader(String url, String body) {
        HttpPost httpPost = this.transformPost(url, body, null);
        return this.httpHeader(httpPost, null);
    }

    /**
     * 执行http post请求，返回指定header
     *
     * @param url  完整的url地址-http://ip:port/path
     * @param body 接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @param headerName 要获取的header名
     * @return 返回json格式
     */
    public String postForHeader(String url, String body, String headerName) {
        HttpPost httpPost = this.transformPost(url, body, null);
        return this.httpHeader(httpPost, headerName);
    }

    /**
     * 执行http delete请求
     *
     * @param url 完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public String delete(String url) {
        HttpDelete httpDelete = this.transformDelete(url, null);
        return this.httpBody(httpDelete);
    }

    /**
     * 执行http delete请求
     *
     * @param url 完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param header 请求头，需要json格式
     * @return 返回json格式
     */
    public String delete(String url, String header) {
        HttpDelete httpDelete = this.transformDelete(url, header);
        return this.httpBody(httpDelete);
    }

    /**
     * 执行http put请求
     *
     * @param url  完整的url地址-http://ip:port/path
     * @return 返回json格式
     */
    public String put(String url) {
        HttpPut httpPut = this.transformPut(url, null, null);
        return this.httpBody(httpPut);
    }

    /**
     * 执行http put请求
     *
     * @param url  完整的url地址-http://ip:port/path
     * @param body 接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @return 返回json格式
     */
    public String put(String url, String body) {
        HttpPut httpPut = this.transformPut(url, body, null);
        return this.httpBody(httpPut);
    }

    /**
     * 执行http put请求
     *
     * @param url  完整的url地址-http://ip:port/path
     * @param body 接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @param header 请求头，json字符串
     * @return 返回json格式
     */
    public String put(String url, String body, String header) {
        HttpPut httpPut = this.transformPut(url, body, header);
        return this.httpBody(httpPut);
    }

}
