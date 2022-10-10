<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/20
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="S" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<s:form action="/tag" method="post">
  <s:textfield name="username" label="用户名"/>
  <s:password name="password" label="密码"/>
  <s:submit value="提交"/>
</s:form>

<s:if test="username=='admin'">管理员</s:if>
<s:elseif test="username=='user'">普通用户</s:elseif>
<s:else>你，啥也不是</s:else>
<s:iterator value="userList" var="s">
    <s:text name="name"></s:text>
    <s:text name="password"></s:text>
</s:iterator>

</body>
</html>
