<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<div>${requestScope.errMsg}</div>
<form action="login" method="post">
    <div><input type="text" name="u_id" placeholder="id"></div>
    <div><input type="password" name="u_pw" placeholder="password"></div>
    <div><input type="submit" value="login"></div>
</form>

<div>
    <a href="join"> join </a>
</div>

</body>
</html>
