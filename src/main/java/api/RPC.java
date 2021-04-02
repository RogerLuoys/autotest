package api;

public interface RPC {

    /**
     * 获取指定服务的对象
     *
     * @param url           完整的RPC应用地址-协议、IP、端口、对象名
     * @param InterfacePath 服务对应的接口路径
     * @return 可直接进行RPC调用的对象，需强制类型转换成对应的接口类型
     */
    @Deprecated
    Object getService(String url, String InterfacePath);

    /**
     * 获取指定服务的对象
     *
     * @param url   完整的RPC应用地址-格式(协议类型://IP:端口/对象名)
     * @param clazz 服务接口对应的class对象
     * @param <T>   -
     * @return 可直接进行RPC调用的对象，于clazz对应的接口类型一致
     */
    <T> T getService(String url, Class<T> clazz);

    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    void forceWait(int second);

}
