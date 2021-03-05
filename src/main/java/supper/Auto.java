package supper;

import api.DUBBO;
import api.HTTP;
import api.UI;
import api.impl.DubboImpl;
import api.impl.HttpClientImpl;
import api.impl.SeleniumImpl;

public class Auto {
    DUBBO dubbo = new DubboImpl();
    HTTP http = new HttpClientImpl();
    UI ui = new SeleniumImpl();

}
