<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="test/check" method="post">
    姓名:<input type="text" name="name"><br/>
    年龄<input type="text" name="age">
    <input type="submit">
</form>

</body>
</html>