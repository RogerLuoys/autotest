package hello;

//import com.luoys.upgrade.uc.api.service.UserService;
import connect.DubboUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class helloTest3 {


//    static public UserService service = null;
//
////    public static String dubboUrl = "dubbo://192.168.10.107:20881?anyhost=true";
//    public static String dubboUrl = "dubbo://10.201.10.183:20881?anyhost=true";
//
//    @BeforeClass
//    public static void init() throws Exception {
//        service =  (UserService) DubboUtil.getService(dubboUrl
//                , "com.luoys.upgrade.uc.api.service"
//                , "test");
//    }
//
//    @Test
//    public void test() throws Exception {
////		AccountService accountService = new AccountService();
//        String result = service.sayHello("test");
//        System.out.println(result);
//    }
    @Test
    void Test1() {
        String sql = "Update dzb_wechat_follow_user SET is_delete=1 WHERe external_user_id='wmNEBPCQAAtnySdraxIhuc1ZvAI1pd7A';";
        int endIndex = sql.toLowerCase().indexOf(" set ");
        String tableName = sql.substring(7, endIndex);
        int startIndex = sql.toLowerCase().indexOf(" where ");
        String condition = sql.substring(startIndex);
        String selectSql = "select " + tableName + condition;
        System.out.println(selectSql);
    }

}
