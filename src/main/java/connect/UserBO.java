package connect;

//import com.sun.istack.internal.NotNull;
import lombok.*;

// lombok自动生成 set、get、toString等方法
//@ToString
@Data
public class UserBO {
//    @Setter(value = AccessLevel.PUBLIC)
//    @Getter
//    @NotNull
    private String userid;

    private String username;
    private String userpw;

    public UserBO() {

    }
}
