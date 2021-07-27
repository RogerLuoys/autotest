package api;

import org.apache.dubbo.rpc.service.GenericService;

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
     * 获取泛化调用接口
     *
     * @param url           完整的RPC应用地址-格式(协议类型://IP:端口/对象名)
     * @param interfaceName 服务接口对应的名称(如：UserService.class.getName())
     * @return 泛化接口
     */
    GenericService getGenericService(String url, String interfaceName);

    /**
     * 通过泛化调用的形式直接调用目标方法
     *
     * @param url           完整的RPC应用地址-格式(协议类型://IP:端口/对象名)
     * @param interfaceName 服务接口对应的名称(如：UserService.class.getName())
     * @param methodName    目标方法的名称
     * @param paramTypeList 目标方法入参类型列表(如：new String[] {"java.lang.String"},...)
     * @param paramList     目标方法入参列表(如：new Object[]{"入参一"},...)
     * @return 调用结果的json字符串
     */
    String invoke(String url, String interfaceName, String methodName, String[] paramTypeList, Object[] paramList);

    void invoke();

    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    void forceWait(int second);

}
