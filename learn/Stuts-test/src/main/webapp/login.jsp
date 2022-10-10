<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<body>
登录界面<br />
<form action="" method="post">
    账号：<input type="text" name="loginid" /><br />
    密码：<input type="password" name="loginpwd" /><br />
    <input type="submit" value="登录" />
</form>

登录界面<br />
<form action="log/login-adminLogin" method="post">
    <input type="submit" value="登录" />
</form>

<form action="log/login-normLogin" method="post">
    <input type="submit" value="登录" />
</form>
</body>