package api.impl;

import api.RPC;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

public class RPCDubboImpl implements RPC {

    private static final String DEFAULT_NAME = "AutomationTester";

    @Override
    public Object getService(String url, String InterfacePath) {
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<?> reference = new ReferenceConfig();
        // 如果点对点直连，可以用reference.setUrl()指定目标地址，设置url后将绕过注册中心，
        // 其中，协议对应provider.setProtocol()的值，端口对应provider.setPort()的值，
        // 路径对应service.setPath()的值，如果未设置path，缺省path为接口名
        reference.setUrl(url);
        reference.setApplication(new ApplicationConfig(DEFAULT_NAME));
        reference.setInterface(InterfacePath);
        return reference.get();
    }

    @Override
    public <T> T getService(String url, Class<T> clazz) {
        ReferenceConfig<T> reference = new ReferenceConfig();
        reference.setUrl(url);
        reference.setApplication(new ApplicationConfig(DEFAULT_NAME));
        reference.setInterface(clazz.getName());
        return reference.get();
    }
}
