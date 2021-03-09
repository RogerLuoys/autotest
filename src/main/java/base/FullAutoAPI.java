package base;

import api.HTTP;
import api.RPC;
import api.UI;
import api.impl.HttpClientImpl;
import api.impl.RPCDubboImpl;
import api.impl.SeleniumImpl;

public class FullAutoAPI {
    public RPC rpc = new RPCDubboImpl();
    public HTTP http = new HttpClientImpl();
    public UI ui = new SeleniumImpl();
}
