<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/6
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
    <s:debug></s:debug><br>
    <s:property value="name"/>
    <s:property value="student.age"/>
    欢迎你,${name},你今年${age}
</body>
</html>
