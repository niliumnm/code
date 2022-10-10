package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class timeInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        long start = System.currentTimeMillis();
        //执行方，并把他的结果记录
        String result = invocation.invoke();
        long end = System.currentTimeMillis();
        System.out.println("运行时间" + (end - start));
        //把执行结果返回，xml才知道action的运行结果"success"
        return result;
    }
}
