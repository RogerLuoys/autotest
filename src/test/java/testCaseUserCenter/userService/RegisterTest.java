package testCaseUserCenter.userService;

import com.luoys.upgrade.uc.share.dto.UserDTO;
import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import testBase.userCenter.UserCenterTestBase;

public class RegisterTest extends UserCenterTestBase {

    private final String serviceURL = URL + "com.luoys.upgrade.uc.share.service.UserService";
    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);

    @AfterClass
    void resetEnv() {
        auto.ucDB.delete("delete from point where owner_id in (select user_id from user where login_name='DUBBOAuto')");
        auto.ucDB.delete("delete from user where login_name='DUBBOAuto';");
    }

    @Test(description = "正常注册")
    void Test1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("接口自动化注册的用户名");
        userDTO.setLoginName("DUBBOAuto");
        userDTO.setPassword("auto123456");
        userService.register(userDTO);
        String password = auto.ucDB.selectOneCell("select pass_word from user where login_name='DUBBOAuto' and is_delete=0;");
        Assert.assertEquals(password, "auto123456", "验证正常注册结果");
    }

    @Test(description = "注册登录名重复")
    void Test2() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("接口自动化注册的用户名");
        userDTO.setLoginName("autoTest01");
        userDTO.setPassword("123456");
        String result = auto.jsonUtil.toString(userService.register(userDTO));
        String message = auto.jsonUtil.getBaseData(result, "message");
        Assert.assertEquals(message, "登录名已被注册", "验证登录名重复注册结果");
    }

}
