package testCaseUserCenter.userService;

import com.luoys.upgrade.uc.share.dto.UserDTO;
import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testCaseUserCenter.UserCenterTestBase;

public class RegisterTest extends UserCenterTestBase {

    private final String serviceURL = auto.config.URL + "com.luoys.upgrade.uc.share.service.UserService";
    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);

    @BeforeClass
    void resetEnv() {
        Reporter.log("还原数据");
        auto.ucDB.delete("delete from point where owner_id in (select user_id from user where login_name='DUBBOAuto')");
        auto.ucDB.delete("delete from user where login_name='DUBBOAuto';");
    }

    @Test(description = "正常注册")
    void test1() {
        Reporter.log("数据准备");
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("接口自动化注册的用户名");
        userDTO.setLoginName("DUBBOAuto");
        userDTO.setPassword("auto123456");

        Reporter.log("调用register方法");
        userService.register(userDTO);

        Reporter.log("验证注册结果");
        String password = auto.ucDB.selectOneCell("select pass_word from user where login_name='DUBBOAuto' and is_delete=0;");
        Assert.assertEquals(password, "auto123456", "验证正常注册结果");
    }


    @Test(description = "注册登录名重复")
    void test2() {
        Reporter.log("数据准备");
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("接口自动化注册的用户名");
        userDTO.setLoginName("autoTest01");
        userDTO.setPassword("123456");

        Reporter.log("调用register方法");
        String result = auto.jsonUtil.toString(userService.register(userDTO));

        Reporter.log("验证注册结果");
        String message = auto.jsonUtil.getBaseData(result, "message");
        Assert.assertEquals(message, "登录名已被注册", "验证登录名重复注册结果");
    }

}
