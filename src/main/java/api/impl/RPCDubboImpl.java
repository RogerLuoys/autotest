package api.impl;

import api.RPC;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

public class RPCDubboImpl implements RPC {

    private static final String DEFAULT_NAME = "AutomationTester";

    /**
     * 获取指定服务的对象
     *
     * @param url           完整的RPC应用地址-协议、IP、端口、对象名
     * @param InterfacePath 服务对应的接口路径
     * @return 可直接进行RPC调用的对象，需强制类型转换成对应的接口类型
     */
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

    /**
     * 获取指定服务的对象
     *
     * @param url   完整的RPC应用地址-格式(协议类型://IP:端口/对象名)
     * @param clazz 服务接口对应的class对象
     * @param <T>   -
     * @return 可直接进行RPC调用的对象，于clazz对应的接口类型一致
     */
    @Override
    public <T> T getService(String url, Class<T> clazz) {
        ReferenceConfig<T> reference = new ReferenceConfig();
        reference.setUrl(url);
        reference.setApplication(new ApplicationConfig(DEFAULT_NAME));
        reference.setInterface(clazz.getName());
        return reference.get();
    }

    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    @Override
    public void forceWait(int second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
