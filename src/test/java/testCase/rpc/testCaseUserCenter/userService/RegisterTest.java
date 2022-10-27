package testCase.rpc.testCaseUserCenter.userService;

import org.testng.annotations.Test;
import testCase.rpc.testCaseUserCenter.UserCenterTestBase;

public class RegisterTest extends UserCenterTestBase {

//    private final String serviceURL = config.Config.URL + "com.luoys.upgrade.uc.share.service.UserService";
//    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);
//
//    @BeforeClass
//    void resetEnv() {
//        Reporter.log("还原数据");
//        auto.ucDB.delete("delete from point where owner_id in (select user_id from user where login_name='DUBBOAuto')");
//        auto.ucDB.delete("delete from user where login_name='DUBBOAuto';");
//    }
//
//    @Test(description = "正常注册")
//    void test1() {
//        Reporter.log("数据准备");
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserName("接口自动化注册的用户名");
//        userDTO.setLoginName("DUBBOAuto");
//        userDTO.setPassword("auto123456");
//
//        Reporter.log("调用register方法");
//        userService.register(userDTO);
//
//        Reporter.log("验证注册结果");
//        String password = auto.ucDB.selectOneCell("select pass_word from user where login_name='DUBBOAuto' and is_delete=0;");
//        Assert.assertEquals(password, "auto123456", "验证正常注册结果");
//    }
//
//
//    @Test(description = "注册登录名重复")
//    void test2() {
//        Reporter.log("数据准备");
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserName("接口自动化注册的用户名");
//        userDTO.setLoginName("autoTest01");
//        userDTO.setPassword("123456");
//
//        Reporter.log("调用register方法");
//        String result = auto.jsonUtil.toString(userService.register(userDTO));
//
//        Reporter.log("验证注册结果");
//        String message = auto.jsonUtil.getBaseData(result, "message");
//        Assert.assertEquals(message, "登录名已被注册", "验证登录名重复注册结果");
//    }

    @Test(description = "正常注册")
    public void test() {
        // 数据还原，删除上次注册的用户
        auto.sql.uc("delete from point where owner_id in (select user_id from user where login_name='DUBBOAuto')");
        auto.sql.uc("delete from user where login_name='DUBBOAuto';");

        // 调用接口注册
        auto.rpc.invoke("com.luoys.upgrade.uc.share.service.UserService#register", "com.luoys.upgrade.uc.share.dto.UserDTO", "{\"userName\":\"接口自动化注册的用户名\",\"loginName\":\"DUBBOAuto\",\"password\":\"auto123456\"}");

        // 校验结果
        String password = auto.sql.uc("select pass_word from user where login_name='DUBBOAuto' and is_delete=0;");
        auto.assertion.isEquals(password, "auto123456");
    }

}
