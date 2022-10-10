<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<h2>Hello World!</h2>
<%--"/exe/register!login"--%>
<form action="/exe/register-login" method="post">
    <input type="text" name="user.name">
    <input type="text" name="user.password">
    <input type="submit">
</form>
<form action="/test" method="post">
    <input type="text" name="page">
    <input type="submit">
</form>

</body>
</html>
