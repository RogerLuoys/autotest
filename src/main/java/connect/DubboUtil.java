package connect;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

public class DubboUtil {
    public DubboUtil() {
    }

    public static Object getService(String url, String className, String clientName) throws Exception {
        ReferenceConfig<?> reference = new ReferenceConfig();
        reference.setApplication(new ApplicationConfig(clientName));
        reference.setInterface(className);
        reference.setUrl(url);
        reference.setTimeout(30000);
        Object service = reference.get();
        return service;
    }

    public static Object getGroupService(String url, String className, String groupName, String clientName) throws Exception {
        ReferenceConfig<?> reference = new ReferenceConfig();
        reference.setApplication(new ApplicationConfig(clientName));
        reference.setInterface(className);
        reference.setUrl(url);
        if (null != groupName && groupName.length() != 0) {
            reference.setGroup(groupName);
        }

        reference.setTimeout(30000);
        Object service = reference.get();
        return service;
    }
}
