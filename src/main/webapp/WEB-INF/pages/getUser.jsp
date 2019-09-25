<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>

</head>
<body>
<table border="1">
    <th>First Name</th>
    <th>Last Name</th>
    <th>Login</th>

    <c:forEach items="${user}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>

            <form action="/edit/${user.login}" method="post">
                <p>you can to change lastname</p>
                <input type="text" placeholder="lastName" name="lastName">
                <input type="submit" value="save">
            </form>
            <td>${user.login}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>