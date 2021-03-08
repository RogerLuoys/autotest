package api;

import java.util.Map;

public interface HTTP {

    String doGet(String url);

    String doGet(String url, Map<String, ?> params);

    String doGet(String url, Object params);

//    String doPost(String url, Map<String, ?> params);

    String doPost(String url, Object data);

}
