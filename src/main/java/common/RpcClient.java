package common;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

public class RpcClient {

    private static final String DEFAULT_NAME = "AutomationTester";

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
            System.out.println("result[setUser]："+ JSON.toJSONString(res)); // 响应结果:result[setUser]：{name=Tom, class=com.xxx.api.service.User, age=24}
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
