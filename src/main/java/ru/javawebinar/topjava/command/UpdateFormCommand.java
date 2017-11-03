package ru.javawebinar.topjava.command;

import ru.javawebinar.topjava.dao.MealDAO;
import javax.servlet.http.HttpServletRequest;

public class UpdateFormCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        MealDAO mealDAO = new MealDAO();

        request.setAttribute("meal", mealDAO.getEntityByID(id));

        return "/jsps/updateMeal.jsp";
    }
}
