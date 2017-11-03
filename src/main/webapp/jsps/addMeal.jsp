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
        <h3><a href="/meals">назад</a></h3>
        <h3>Добавить приём пищи</h3>
        <form action="/meals?command=add_meal" method="post">
            <div>
                <label for="description">Описание приема пищи:</label>
                <input type="text" name="description" id="description" placeholder="Назовите ваш прием пищи">
            </div>
            <div>
                <label for="caloriesNumber">Количество калорий:</label>
                <input type="number" name="caloriesNumber" id="caloriesNumber" min="0" max="100000" step="10" value="0">
            </div>
            <div>
                <label for="date">Дата:</label>
                <input type="datetime-local" name="date" id="date" placeholder="Дата в формате:">
            </div>
            <input type="submit" name="addMeal" value="Добавить">
        </form>
    </div>
</body>
</html>
