package api.impl;

import api.HTTP;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

//1 创建HttpClient客户端
//2 创建Http请求
//3 设置
//4 发送请求
public class HttpClientImpl implements HTTP {


    private String httpGet(String url, Map<String, String> header) {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String results = null;
        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头
        for (String key : header.keySet()) {
            httpGet.setHeader(key, header.get(key));
        }
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            results = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpResponse.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    @Override
    public String doGet(String url) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        return httpGet(url, header);
    }

    private URIBuilder getURIBuilder (String url) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            return uriBuilder;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String transformMap2String (String url, Map<String, ?> params) {
        URIBuilder uriBuilder = getURIBuilder(url);
        Iterator iterator = params.keySet().iterator();
        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            uriBuilder.setParameter(key, params.get(key).toString());
        }
        return uriBuilder.toString();
    }

    @Override
    public String doGet(String url, Map<String, ?> params) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        return httpGet(transformMap2String(url, params), header);
    }

    private String transformObject2String (String url, Object obj) {
        URIBuilder uriBuilder = getURIBuilder(url);
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i = 0; i < fields.length; i++) {
            // 对于每个属性，获取属性名
            String varName = fields[i].getName();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o;
                try {
                    o = fields[i].get(obj);
                    if (o != null) {
                        uriBuilder.setParameter(varName, o.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        return uriBuilder.toString();
    }

    @Override
    public String doGet(String url, Map<String, ?> params, Map<String, String> header) {
//        Map<String, String> header = new HashMap<>();
//        header.put("Connection", "keep-alive");
        return httpGet(transformObject2String(url, params), header);
    }

    private String httpPost(String url, String jsonData, Map<String, String> header) {
        // 创建 HttpClient 客户端

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost(url);
        for (String key : header.keySet()) {
            httpPost.setHeader(key, header.get(key));
        }
        CloseableHttpResponse httpResponse = null;
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(jsonData);
            stringEntity.setContentType("application/json");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            // 设置 HttpPost 参数
            httpPost.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpResponse.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

//    @Override
//    public String doPost(String url, Map<String, ?> params) {
//        return httpPost(url, params);
//    }

    @Override
    public String doPost(String url, Object data) {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        String jsonData = JSON.toJSONString(data);
        return httpPost(url, jsonData, header);
    }

    @Override
    public String doPost(String url, Object data, Map<String, String> header) {
        String jsonData = JSON.toJSONString(data);
        return httpPost(url, jsonData, header);
    }

    @Override
    public String doPost(String url, Object data, Map<String, String> header, Map<String, ?> params) {
        String jsonData = JSON.toJSONString(data);
        return httpPost(transformMap2String(url, params), jsonData, header);
    }
}
