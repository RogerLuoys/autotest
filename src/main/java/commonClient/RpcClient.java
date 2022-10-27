package commonClient;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class RpcClient {

    private static final String DEFAULT_NAME = "AutomationTester";

    public void invokeOld() {
        forceWait(1);
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
            System.out.println("result[setUser]："+ JSON.toJSONString(res)); // 响应结果:result[setUser]：{name=Tom, class=com.xxx.oldCode.api.service.User, age=24}
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public String invokeOld2() {
        //创建ApplicationConfig
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-call-consumer");
        //创建注册中心配置,zookeeper://118.24.117.181:2181,dubbo://118.24.117.181:20881
        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://10.199.142.107:2181");
        //创建服务引用配置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        //设置接口,com.luoys.upgrade.uc.share.service.UserService
        referenceConfig.setInterface("com.luoys.upgrade.uc.share.service.UserService");
//        applicationConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setUrl("dubbo://118.24.117.181:20881");
        //重点：设置为泛化调用
        //注：不再推荐使用参数为布尔值的setGeneric函数
        //应该使用referenceConfig.setGeneric("true")代替
        referenceConfig.setGeneric("true");
        //设置超时时间
        referenceConfig.setTimeout(7000);

        //获取服务，由于是泛化调用，所以获取的一定是GenericService类型
        GenericService genericService = referenceConfig.get();

        Map<String, Object> params = new HashMap<>();
        params.put("userId", "416170902167365");
        params.put("userName", "这是修改后的名字");
        System.err.println(JSON.toJSONString(params));

        //使用GenericService类对象的$invoke方法可以代替原方法使用
        //第一个参数是需要调用的方法名,queryByUserId
        //第二个参数是需要调用的方法的参数类型数组，为String数组，里面存入参数的全类名。
        //第三个参数是需要调用的方法的参数数组，为Object数组，里面存入需要的参数。
        Object result = genericService.$invoke("modifyUser", new String[]{"com.luoys.upgrade.uc.share.dto.UserDTO"}, new Object[]{params});
        //打印结果
        System.out.println(JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }

    public String invokeOld3() {
        //创建ApplicationConfig
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-call-consumer");
        //创建注册中心配置,zookeeper://118.24.117.181:2181,dubbo://118.24.117.181:20881
        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://10.199.142.107:2181");
        //创建服务引用配置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        //设置接口,com.luoys.upgrade.uc.share.service.UserService
        referenceConfig.setInterface("com.luoys.upgrade.uc.share.service.UserService");
//        applicationConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setUrl("dubbo://118.24.117.181:20881");
        //重点：设置为泛化调用
        //注：不再推荐使用参数为布尔值的setGeneric函数
        //应该使用referenceConfig.setGeneric("true")代替
        referenceConfig.setGeneric("true");
        //设置超时时间
        referenceConfig.setTimeout(7000);

        //获取服务，由于是泛化调用，所以获取的一定是GenericService类型
        GenericService genericService = referenceConfig.get();

        //使用GenericService类对象的$invoke方法可以代替原方法使用
        //第一个参数是需要调用的方法名,queryByUserId
        //第二个参数是需要调用的方法的参数类型数组，为String数组，里面存入参数的全类名。
        //第三个参数是需要调用的方法的参数数组，为Object数组，里面存入需要的参数。
        Object result = genericService.$invoke("queryByUserId", new String[]{"java.lang.String"}, new Object[]{"416160586979148"});
        //打印结果
        System.out.println(JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }

    public String invoke_template() throws InterruptedException {
        //创建ApplicationConfig
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-call-consumer");
        //创建注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        //创建服务引用配置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        //设置接口
        referenceConfig.setInterface("org.apache.dubbo.samples.generic.call.api.HelloService");
        applicationConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        //重点：设置为泛化调用
        //注：不再推荐使用参数为布尔值的setGeneric函数
        //应该使用referenceConfig.setGeneric("true")代替
        referenceConfig.setGeneric("true");
        //设置异步，不必须，根据业务而定。
        referenceConfig.setAsync(true);
        //设置超时时间
        referenceConfig.setTimeout(7000);

        //获取服务，由于是泛化调用，所以获取的一定是GenericService类型
        GenericService genericService = referenceConfig.get();

        //使用GenericService类对象的$invoke方法可以代替原方法使用
        //第一个参数是需要调用的方法名
        //第二个参数是需要调用的方法的参数类型数组，为String数组，里面存入参数的全类名。
        //第三个参数是需要调用的方法的参数数组，为Object数组，里面存入需要的参数。
        Object result = genericService.$invoke("queryByUserId", new String[]{"java.lang.String"}, new Object[]{"416160586979148"});
        //使用CountDownLatch，如果使用同步调用则不需要这么做。
        CountDownLatch latch = new CountDownLatch(1);
        //获取结果
        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((value, t) -> {
            System.err.println("invokeSayHello(whenComplete): " + value);
            latch.countDown();
        });
        //打印结果
        System.err.println("invokeSayHello(return): " + result);
        latch.await();
        return "";
    }

    /**
     * 泛化调用rpc接口
     *
     * @param fullLocation 完整调用地址，如 "dubbo://ip:port/interface#method"
     * @param paramType 被测rpc接口的入参类型
     * @param paramValue 被测rpc接口的入参
     * @return jason格式调用结果
     */
    public String invoke(String fullLocation, String paramType, String paramValue) {

        // 将完整调用地址，截成服务器地址、接口路径和方法名
        String url = fullLocation.substring(0, fullLocation.lastIndexOf("/"));
        String interfaceClass = fullLocation.substring(fullLocation.lastIndexOf("/") + 1, fullLocation.lastIndexOf("#"));
        String methodName = fullLocation.substring(fullLocation.lastIndexOf("#") + 1);

        //创建ApplicationConfig
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-call-consumer");
        //创建服务引用配置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        //设置接口,com.luoys.upgrade.uc.share.service.UserService
        referenceConfig.setInterface(interfaceClass);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setUrl(url);
        //重点：设置为泛化调用
        //注：不再推荐使用参数为布尔值的setGeneric函数
        //应该使用referenceConfig.setGeneric("true")代替
        referenceConfig.setGeneric("true");
        //设置超时时间
        referenceConfig.setTimeout(10000);

        //获取服务，由于是泛化调用，所以获取的一定是GenericService类型
        GenericService genericService = referenceConfig.get();

        //使用GenericService类对象的$invoke方法可以代替原方法使用
        //第一个参数是需要调用的方法名,queryByUserId
        //第二个参数是需要调用的方法的参数类型数组，为String数组，里面存入参数的全类名。
        //第三个参数是需要调用的方法的参数数组，为Object数组，里面存入需要的参数。
        Object result;
        if (paramType.equals(String.class.getName())) {
            result = genericService.$invoke(methodName, new String[]{paramType}, new Object[]{paramValue});
        } else if (paramType.equals(Integer.class.getName())) {
            result = genericService.$invoke(methodName, new String[]{paramType}, new Object[]{Integer.valueOf(paramValue)});
        } else if (paramType.equals(Long.class.getName())) {
            result = genericService.$invoke(methodName, new String[]{paramType}, new Object[]{Long.valueOf(paramValue)});
        } else {
            result = genericService.$invoke(methodName, new String[]{paramType}, new Object[]{JSON.parseObject(paramValue)});
        }

        //打印结果
        System.out.println(JSON.toJSONString(result));
        referenceConfig.destroy();
        return JSON.toJSONString(result);
    }

    /**
     * 进程睡眠，强制等待
     *
     * @param second 等待的时间-单位秒
     */
    private void forceWait(int second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
