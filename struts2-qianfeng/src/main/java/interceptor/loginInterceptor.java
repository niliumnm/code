package interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;

public class loginInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext ac= ActionContext.getContext();
        Map session = ac.getSession();
        if (session.get("SESSION_USER") == null) {
            System.out.println(session.get("SESSION_USER"));
            return "LOGIN2";
        }
        System.out.println(session.get("SESSION_USER"));
        return invocation.invoke();
    }
}
