package api.impl;

import api.HTTP;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//1 创建HttpClient客户端
//2 创建Http请求
//3 设置
//4 发送请求
public class HttpClientImpl implements HTTP {


    private String httpGet(String url) {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String results = null;
        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet(url);
        // 设置长连接
        httpGet.setHeader("Connection", "keep-alive");

        CloseableHttpResponse httpResponse = null;

        try {
            // 请求并获得响应结果
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

    public String doGet(String url) {
        return httpGet(url);
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

    public String doGet(String url, Map<String, ?> params) {
        return httpGet(transformMap2String(url, params));
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

//    private Map<String, ?> transformObject2Map (String url, Object obj) {
//        Map<String, String> uriBuilder = getURIBuilder(url);
//        Field[] fields = obj.getClass().getDeclaredFields();
//        for(int i = 0; i < fields.length; i++) {
//            // 对于每个属性，获取属性名
//            String varName = fields[i].getName();
//            try {
//                // 获取原来的访问控制权限
//                boolean accessFlag = fields[i].isAccessible();
//                // 修改访问控制权限
//                fields[i].setAccessible(true);
//                // 获取在对象f中属性fields[i]对应的对象中的变量
//                Object o;
//                try {
//                    o = fields[i].get(obj);
//                    if (o != null) {
//                        uriBuilder.setParameter(varName, o.toString());
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                // 恢复访问控制权限
//                fields[i].setAccessible(accessFlag);
//            } catch (IllegalArgumentException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return uriBuilder.toString();
//    }

    public String doGet(String url, Object params) {
        return httpGet(transformObject2String(url, params));
    }

    private String httpPost(String url, Map<String, ?> params) {
        // 创建 HttpClient 客户端

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost(url);
        // 设置长连接
        httpPost.setHeader("Connection", "keep-alive");

        // 创建 HttpPost 参数
        List<BasicNameValuePair> pairList = new ArrayList<>();
        for (String key : params.keySet()) {
            pairList.add(new BasicNameValuePair(key, params.get(key) != null ? params.get(key).toString() : null));
        }
        CloseableHttpResponse httpResponse = null;
        try {
            // 设置 HttpPost 参数
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
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
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String doPost(String url, Map<String, ?> params) {
        return httpPost(url, params);
    }

    @Override
    public String doPost(String url, Object data) {
        return null;
    }
}
