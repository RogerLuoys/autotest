package commonClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//1 创建HttpClient客户端
//2 创建Http请求
//3 设置
//4 发送请求
@Slf4j
public class HttpClient {

    /**
     * 执行http get请求
     *
     * @param url    带入参（如果有）的完整url
     * @param header 请求头
     * @return get请求结果
     */
    private String httpGet(String url, Map<String, String> header) {
        log.info("\n====>执行get请求开始：{}", url);
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头
        for (String key : header.keySet()) {
            httpGet.setHeader(key, header.get(key));
        }
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            log.error("\n====>执行get请求失败！");
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("\n====>关闭请求失败！");
            }
        }
        log.info("\n====>执行get请求成功：{}", result);
        return result;
    }

    /**
     * 执行http post请求，通用方法
     *
     * @param url      带入参（如果有）的完整url
     * @param jsonData json格式的body入参
     * @param header   请求头
     * @return post请求结果
     */
    private String httpPost(String url, String jsonData, Map<String, String> header) {
        log.info("\n====>执行post请求开始：{}\n====>post请求body：{}", url, jsonData);
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost(url);
        for (String key : header.keySet()) {
            httpPost.setHeader(key, header.get(key));
        }
        CloseableHttpResponse httpResponse = null;
        StringEntity stringEntity;
        //设置编码格式避免中文乱码
        stringEntity = new StringEntity(jsonData, StandardCharsets.UTF_8);
        stringEntity.setContentType("application/json");
        try {
            // 设置 HttpPost 参数
            httpPost.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            log.error("\n====>执行post请求失败！");
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("\n====>关闭请求失败！");
            }
        }
        log.info("\n====>执行post请求成功：{}", result);
        return result;
    }


    /**
     * 执行http delete请求(无请求体)
     *
     * @param url    带入参（如果有）的完整url
     * @param header 请求头
     * @return delete请求结果
     */
    private String httpDelete(String url, Map<String, String> header) {
        log.info("\n====>执行delete请求开始：{}", url);
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        HttpDelete httpDelete = new HttpDelete(url);
        for (String key : header.keySet()) {
            httpDelete.setHeader(key, header.get(key));
        }
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpDelete);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            log.error("\n====>执行delete请求失败！");
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("\n====>关闭请求失败！");
            }
        }
        log.info("\n====>执行delete请求成功：{}", result);
        return result;
    }

    /**
     * 执行http put请求，通用方法
     *
     * @param url      带入参（如果有）的完整url
     * @param jsonData json格式的body入参
     * @param header   请求头
     * @return put请求结果
     */
    private String httpPut(String url, String jsonData, Map<String, String> header) {
        log.info("\n====>执行put请求开始：{}\n====>put请求body：{}", url, jsonData);
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        HttpPut httpPut = new HttpPut(url);
        for (String key : header.keySet()) {
            httpPut.setHeader(key, header.get(key));
        }
        CloseableHttpResponse httpResponse = null;
        //设置请求体
        if (jsonData != null) {
            StringEntity stringEntity = new StringEntity(jsonData, StandardCharsets.UTF_8);
            stringEntity.setContentType("application/json");
            httpPut.setEntity(stringEntity);
        }
        try {
            httpResponse = httpClient.execute(httpPut);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            log.error("\n====>执行put请求失败！");
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("\n====>关闭请求失败！");
            }
        }
        log.info("\n====>执行put请求成功：{}", result);
        return result;
    }

    /**
     * 把url字符串转换成URIBuilder对象
     *
     * @param url 完整url
     * @return -
     */
    private URIBuilder getURIBuilder(String url) {
        try {
            return new URIBuilder(url);
        } catch (URISyntaxException e) {
            log.error("\n====>处理请求头url异常");
//            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断字符串是否为空(org.apache.dubbo.common.utils的方法)
     * @param cs 目标字符串
     * @return 字符串为null || 长度为0 || 只包含空格，则返回true
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 把Map变量转换成http的请求头参数
     *
     * @param url    完整的地址
     * @param params 需要往请求头传的参数
     * @return 带参数的完整url地址
     */
    private String transformMap2URL(String url, Map<String, ?> params) {
        URIBuilder uriBuilder = getURIBuilder(url);
        for (String key : params.keySet()) {
            assert uriBuilder != null;
            uriBuilder.setParameter(key, params.get(key).toString());
        }
        assert uriBuilder != null;
        return uriBuilder.toString();
    }

    private Map<String, String> transformJson2Map(String json) {
        Map<String, String> map = new HashMap<>();
        if (isBlank(json)) {
            return map;
        }
        JSONObject jsonObject = JSON.parseObject(json);
        for (String key : jsonObject.keySet()) {
            map.put(key, jsonObject.get(key).toString());
        }
        return map;
    }

    /**
     * 执行http get请求
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public String get(String url) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        return httpGet(url, header);
    }

    /**
     * 执行http get请求
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param header 请求头，需要json格式
     * @return 返回json格式
     */
    public String get(String url, String header) {
        Map<String, String> headerMap = transformJson2Map(header);
        headerMap.put("Connection", "keep-alive");
        return httpGet(url, headerMap);
    }

    /**
     * 执行http delete请求
     *
     * @param url 完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public String delete(String url) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        return httpDelete(url, header);
    }

    /**
     * 执行http delete请求
     *
     * @param url 完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param header 请求头，需要json格式
     * @return 返回json格式
     */
    public String delete(String url, String header) {
        Map<String, String> headerMap = transformJson2Map(header);
        headerMap.put("Connection", "keep-alive");
        return httpDelete(url, headerMap);
    }

    /**
     * 执行http post请求
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @return 返回json格式
     */
    public String post(String url) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        return httpPost(url, null, header);
    }


    /**
     * 执行http post请求
     *
     * @param url  完整的url地址，可带参数-http://ip:port/path?param1=value1
     * @param body 接口传入的请求体，application/json格式
     * @return 返回json格式
     */
    public String post(String url, String body) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        return httpPost(url, body, header);
    }

    public String post(String url, String body, String header) {
        Map<String, String> headerMap = transformJson2Map(header);
        headerMap.put("Connection", "keep-alive");
        return httpPost(url, body, headerMap);
    }

    /**
     * 执行http put请求
     *
     * @param url  完整的url地址-http://ip:port/path
     * @return 返回json格式
     */
    public String put(String url) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        return httpPut(url, null, header);
    }

    /**
     * 执行http put请求
     *
     * @param url  完整的url地址-http://ip:port/path
     * @param body 接口对应的POJO对象或Map对象，传入body中，application/json格式
     * @return 返回json格式
     */
    public String put(String url, String body) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        return httpPut(url, body, header);
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
        Map<String, String> headerMap = transformJson2Map(header);
        headerMap.put("Connection", "keep-alive");
        return httpPut(url, body, headerMap);
    }

    public String execute(String httpDTOJson) {
        JSONObject httpDTO = JSON.parseObject(httpDTOJson);
        String type = httpDTO.getString("type");
        String url = httpDTO.getString("url");
        String header = httpDTO.getString("header");
        String body = httpDTO.getString("body");
        Map<String, String> headerMap = transformJson2Map(header);
        headerMap.put("Connection", "keep-alive");
        if (type.equalsIgnoreCase("get")) {
            return httpGet(url, headerMap);
        } else if (type.equalsIgnoreCase("delete")) {
            return httpDelete(url, headerMap);
        } else if (type.equalsIgnoreCase("post")) {
            return httpPost(url, body, headerMap);
        } else if (type.equalsIgnoreCase("put")) {
            return httpPost(url, body, headerMap);
        }
        return null;
    }

}
