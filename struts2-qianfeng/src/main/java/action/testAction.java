package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Data;
import org.apache.struts2.ServletActionContext;
import pojo.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Data
public class testAction extends ActionSupport implements ModelDriven<User> {

    private User user;

    private String page;

    private String name;
    private String password;
    public String login() {
        System.out.println("登录");
        ActionContext ac = ActionContext.getContext();
        Map request = (Map)ac.get("request");

        Map application = ac.getApplication();
        Map session = ac.getSession();
        session.put("object_test", "i'm session");

        HttpServletRequest request1 = ServletActionContext.getRequest();
        HttpSession session1 = request1.getSession();
        HttpServletResponse response = ServletActionContext.getResponse();
        request1.setAttribute("object_test","i'm request");

        return "login";
    }

    public String register() {
        System.out.println("注册");
        return "register";
    }

    public String hello() {
        System.out.println("你好");
        return SUCCESS;
    }


    public User getModel() {
        if (user == null) {
            return new User();
        }
        return user;
    }
}
