package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;

import java.util.Map;

@Data
public class ValAction extends ActionSupport {
    String username;

    //重写validate方法
//    @Override
//    public void validate() {
//        if (username == null || username.trim().length() < 6) {
//            addFieldError("username", "请输入用户名，且长度大于6");
//        }
//    }

    public void validateLogin() {
        if (username == null || username.trim().length() < 6) {
            addFieldError("username", "请输入用户名，且长度大于6");
        }
    }

    public String login() {

        return SUCCESS;
    }
    public String register() {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        session.put("SESSION_USER", "hello");
        System.out.println(session.get("SESSION_USER"));
        return SUCCESS;
    }
}
