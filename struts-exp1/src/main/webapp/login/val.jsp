<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/10/10
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>验证</title>
</head>
<body>
<form action="validate" method="post">
    <h1>重写validate</h1>
    用户名: <input type="text" name="username"><br>
    密码 : <input type="text" name="password"><br>
    <input type="submit" value="登录" />
    <s:fielderror></s:fielderror>
</form>

<form action="validate" method="post">
    <h1>xml配置</h1>
    用户名: <input type="text" name="username"><br>
    密码 : <input type="text" name="password"><br>
    <input type="submit" value="登录" />
    <s:fielderror></s:fielderror>
</form>
</body>
</html>
