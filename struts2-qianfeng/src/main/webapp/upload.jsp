<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/21
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="upload"> <!--name要和action里的属性对应-->
    <input type="submit">
</form>
</body>
</html>
