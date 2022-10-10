<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/20
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="valLog" method="post">
    <input type="text" >
    <input type="submit" name="提交">
</form>
<form action="valReg" method="post">
    <input type="text">
    <input type="submit" name="提交">
</form>

<s:fielderror></s:fielderror>
</body>
</html>
