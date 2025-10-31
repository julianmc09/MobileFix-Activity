<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="/login" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username"/>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password"/>
        </div>
        <input type="submit" value="Log In"/>
    </form>
</body>
</html>
