<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>登录</title>
</head>
<body>
    <h1>登录</h1>
    欢迎你,${user.name},你今年${user.password}
    ${sessionScope.object_test}
    ${requestScope.object_test}

</body>
</html>
