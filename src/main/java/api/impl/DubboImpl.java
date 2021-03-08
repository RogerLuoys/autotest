package api.impl;

import api.DUBBO;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

public class DubboImpl implements DUBBO {

    Object dubboService () {
        ReferenceConfig<?> reference = new ReferenceConfig(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        // 如果点对点直连，可以用reference.setUrl()指定目标地址，设置url后将绕过注册中心，
        // 其中，协议对应provider.setProtocol()的值，端口对应provider.setPort()的值，
        // 路径对应service.setPath()的值，如果未设置path，缺省path为接口名
        reference.setUrl("dubbo://10.20.130.230:20880/com.xxx.XxxService");
        reference.setApplication(new ApplicationConfig("DubboTester"));
        return null;

    }
}
