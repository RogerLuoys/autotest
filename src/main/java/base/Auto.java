package base;

import api.DUBBO;
import api.HTTP;
import api.UI;
import api.impl.DubboImpl;
import api.impl.HttpClientImpl;
import api.impl.SeleniumImpl;

public class Auto {
    public DUBBO dubbo = new DubboImpl();
    public HTTP http = new HttpClientImpl();
    public UI ui = new SeleniumImpl();
    public HTTP http1 () {
        http.doGet("");
        return new HttpClientImpl();
    }
}
