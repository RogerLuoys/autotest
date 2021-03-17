package supperTestClass;

import api.DB;
import api.UI;
import api.impl.DBJdbcTemplateImpl;
import api.impl.SeleniumImpl;

public class UIFullAPI {
    public final UI ui = new SeleniumImpl();
    public final DB db = new DBJdbcTemplateImpl();
    public final UITask task = new UITask();
}
