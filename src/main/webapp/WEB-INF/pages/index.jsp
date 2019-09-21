<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search</title>
</head>
<body>
${login}
<form action="/getUser " method="get">

    <p>Find user</p>

    <p><input type="text" placeholder="login" name="login"></p>
    <p><input type="submit" value="Find"></p>
    </form>
<p>
<p>
<p>
    <form action="/addUser" method="post">
    <p>Add new User</p>
        <table border="1">
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Login</th>

            <tr>
                <td><input type="text" placeholder="firstName" name="firstName"></td>
                <td><input type="text" placeholder="lastName" name="lastName"></td>
                <td><input type="number" placeholder="age" name="age"></td>
                <td><input type="text" placeholder="login" name="login"></td>
            </tr>
        </table>

        <p><input type="submit" value="submit"></p>
    </form>

</body>
</html>
