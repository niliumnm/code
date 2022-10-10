<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/26
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1>动态调用</h1>
<form action="login-static" method="post">
    复选框+属性传值<br>
    账号: <input type="text" name="username"><br>
    密码: <input type="password" name="password"><br>
    <s:checkbox value="管理员" name="admin"></s:checkbox>
    <input type="submit">
</form>
<hr>
!调用<br>
登录界面<br />
<form action="login!normLogin" method="post">
    普通登录<br>
    账号: <input type="text" name="username"><br>
    密码: <input type="password" name="password"><br>
    <input type="submit" value="登录" />
</form>

<form action="login!adminLogin" method="post">
    管理员<br>
    账号: <input type="text" name="username"><br>
    密码: <input type="password" name="password"><br>
    <input type="submit" value="登录" />
</form>

<hr>
通配符调用<br />
<form action="login-adminLogin" method="post">
    管理员<br>
    账号: <input type="text" name="username"><br>
    密码: <input type="password" name="password"><br>
    管理员<input type="submit" value="登录" />
</form>

<form action="login-normLogin" method="post">
    普通登录<br>
    账号: <input type="text" name="username"><br>
    密码: <input type="password" name="password"><br>
    <input type="submit" value="登录" />
</form>
<hr>
<h1>传值</h1>
1. 属性驱动 上面全都是
<hr>
2. 阈值驱动
<form action="data" method="post">
    普通登录<br>
    账号: <input type="text" name="student.name"><br>
    密码: <input type="password" name="student.password"><br>
    <input type="submit" value="登录" />
</form>
3. 模型驱动
<form action="data" method="post">
    普通登录<br>
    账号: <input type="text" name="name"><br>
    密码: <input type="password" name="password"><br>
    <input type="submit" value="登录" />
</form>
<hr>
<h1>类型转换</h1>
<form action="data" method="post">
    类型转换<br>
    <input type="text" name="birthday"><br>
    <input type="submit" value="登录" />
</form>



</body>
</html>
