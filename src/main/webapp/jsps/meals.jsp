<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<!DOCTYPE html>
<html>
<head>
    <title>Приёма пищи</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h3><a href="${pageContext.request.contextPath}/index.html">Home</a></h3>
<h2>Приём пищи</h2>
<table>
    <thead>
    <th>Название</th>
    <th>Количество калорий</th>
    <th>Дата</th>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.isExceed() ? 'calories_over_limit' : 'calories_under_limit'}">
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td><javatime:format value="${meal.getDateTime()}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
