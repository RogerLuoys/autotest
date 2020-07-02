package connect;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

public class RPC {
    public Object getService() {
        ReferenceConfig<?> referenceConfig = new ReferenceConfig<Object>();
        referenceConfig.setApplication(new ApplicationConfig("test"));
        referenceConfig.setInterface("");
        referenceConfig.setUrl("");
        referenceConfig.setTimeout(30000);
        Object service = referenceConfig.get();
        return service;
    }
}