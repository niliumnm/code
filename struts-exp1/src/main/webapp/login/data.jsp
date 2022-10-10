<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/26
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>阈值驱动</h1>
获取的值<br>
姓名：${student.name}, 密码：${student.password}
<h1>模型驱动</h1>
获取的值<br>
姓名：${name}, 密码：${password}
<h1>类型转换</h1>
存入：${birthday}<br>
取出：<s:property value="birthday"></s:property>
</body>
</html>
