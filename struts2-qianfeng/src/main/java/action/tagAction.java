package action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class tagAction extends ActionSupport {
    private List<String> list=new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private String username;
    public String login(){
        userList.add(new User("张三", "1148145"));
        userList.add(new User("李四", "1148145"));
        return SUCCESS;
    }
}
