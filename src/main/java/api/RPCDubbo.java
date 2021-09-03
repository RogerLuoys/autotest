package api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RPCDubbo {

    private static final String DEFAULT_NAME = "AutomationTester";

    /**
     * 获取指定服务的对象
     *
     * @param url           完整的RPC应用地址-协议、IP、端口、对象名
     * @param InterfacePath 服务对应的接口路径
     * @return 可直接进行RPC调用的对象，需强制类型转换成对应的接口类型
     */
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
    public <T> T getService(String url, Class<T> clazz) {
        ReferenceConfig<T> reference = new ReferenceConfig();
        reference.setUrl(url);
        reference.setApplication(new ApplicationConfig(DEFAULT_NAME));
        reference.setInterface(clazz.getName());
        return reference.get();
    }

    /**
     * 获取泛化调用接口
     *
     * @param url           完整的RPC应用地址-格式(协议类型://IP:端口/对象名)
     * @param interfaceName 服务接口对应的名称(如：UserService.class.getName())
     * @return 泛化接口
     */
    public GenericService getGenericService(String url, String interfaceName) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig();
        reference.setUrl(url);
        reference.setApplication(new ApplicationConfig(DEFAULT_NAME));
        reference.setInterface(interfaceName);
        reference.setGeneric(true);
        return reference.get();
    }

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
    public String invoke(String url, String interfaceName, String methodName, String[] paramTypeList, Object[] paramList) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig();
        reference.setUrl(url);
        reference.setApplication(new ApplicationConfig(DEFAULT_NAME));
        reference.setInterface(interfaceName);
        reference.setGeneric(true);
        GenericService genericService = reference.get();
        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke(methodName, paramTypeList, paramList);
        return JSON.toJSONString(result);
    }

//    public void invoke() {
//        try {
//            // 引用远程服务
//            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
//            // 弱类型接口名
//            reference.setInterface("com.luoys.upgrade.uc.share.service.UserService");
//            reference.setUrl("dubbo://118.24.117.181:20881/com.luoys.upgrade.uc.share.service.UserService");
//            reference.setRetries(0);
//            // RpcContext中设置generic=gson
//            RpcContext.getContext().setAttachment("generic","gson");
//            // 声明为泛化接口
//            reference.setGeneric("true");
//            reference.setCheck(false);
//            GenericService genericService = ReferenceConfigCache.getCache().get(reference);
//            // 传递参数对象的json字符串进行一次调用
//            Object res = genericService.$invoke("queryByUserId", new String[]{"java.lang.String"}, new Object[]{"416160586979148"});
//            System.out.println("result[setUser]："+JSON.toJSONString(res)); // 响应结果:result[setUser]：{name=Tom, class=com.xxx.api.service.User, age=24}
//        } catch (Throwable ex) {
//            ex.printStackTrace();
//        }
//    }

    public void invoke() {
        try {
            // 引用远程服务
            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
            // 弱类型接口名
            reference.setInterface("com.luoys.upgrade.uc.share.service.UserService");
            reference.setUrl("dubbo://118.24.117.181:20881/com.luoys.upgrade.uc.share.service.UserService");
//            reference.setVersion("1.0");
            reference.setRetries(0);
            // RpcContext中设置generic=gson
            RpcContext.getContext().setAttachment("generic","gson");
            // 声明为泛化接口
            reference.setGeneric("true");
            reference.setCheck(false);
            GenericService genericService = reference.get();
//            GenericService genericService = ReferenceConfigCache.getCache().get(reference);
            // 传递参数对象的json字符串进行一次调用

            Map<String, Object> params = new HashMap<>();
            params.put("userId", "416170902167365");
            params.put("userName", "这是修改后的名字");

            // "{\"userId\":\"416170902167365\",\"userName\":\"这是修改后的名字\"}"
            Object res = genericService.$invoke("modifyUser", new String[]{"com.luoys.upgrade.uc.share.dto.UserDTO"}, new Object[]{params});
            System.out.println("result[setUser]："+JSON.toJSONString(res)); // 响应结果:result[setUser]：{name=Tom, class=com.xxx.api.service.User, age=24}
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    public void forceWait(int second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
