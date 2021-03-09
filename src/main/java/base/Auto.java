package base;

import api.HTTP;
import api.RPC;
import api.UI;
import api.impl.HttpClientImpl;
import api.impl.RPCDubboImpl;
import api.impl.SeleniumImpl;

public class Auto {
    public RPC rpc = new RPCDubboImpl();
    public HTTP http = new HttpClientImpl();
    public UI ui = new SeleniumImpl();
    public HTTP http1 () {
        http.doGet("");
        return new HttpClientImpl();
    }
}
