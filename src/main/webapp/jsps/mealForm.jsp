
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добавление приема пищи</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="add_meal_form_container">
    <h3><a href="${pageContext.request.contextPath}/meals">назад</a></h3>
    <h3>${meal == null ? 'Добавить приём пищи' : 'Изменить'}</h3>
    <p style="color: red">${caloriesNotNumberError}</p>

    <c:choose>
        <c:when test="${meal == null}">
            <form action="${pageContext.request.contextPath}/meals?command=insertMeal" method="post">
        </c:when>
        <c:otherwise>
            <form action="${pageContext.request.contextPath}/meals?command=updateMeal" method="post">
        </c:otherwise>
    </c:choose>

        <c:if test="${meal != null}">
            <input type="hidden" name="id" value="${meal.getId()}">
        </c:if>
        <div>
            <label for="description">Описание приема пищи:</label>
            <input type="text" name="description" id="description" value="<c:if test="${meal != null}">${meal.getDescription()}</c:if>" required>
        </div>
        <div>
            <label for="caloriesNumber">Количество калорий:</label>
            <input type="number" name="caloriesNumber" id="caloriesNumber" min="0" max="100000" step="10" value="<c:if test="${meal != null}">${meal.getCalories()}</c:if>" required>
        </div>
        <div>
            <label for="date">Дата:</label>
            <input type="datetime-local" name="date" id="date" value="<c:if test="${meal != null}">${meal.getDateTime()}</c:if>" required>
        </div>
        <input type="submit" name="addMeal" value="${meal == null ? 'Добавить' : 'Изменить'}">
    </form>
</div>
</body>
</html>
