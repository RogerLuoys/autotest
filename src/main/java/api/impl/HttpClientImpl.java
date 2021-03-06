package api.impl;

import api.HTTP;

public class HttpClientImpl implements HTTP {

    @Override
    public void doGet(String url) {
        System.out.println("test");
    }
}
