package ru.javawebinar.topjava.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger logger = getLogger(UserServlet.class);
    private volatile MealDAO mealDAO;

    @Override
    public void init() throws ServletException {
        mealDAO = new MealDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");

        if (command == null || command.isEmpty()) {
            listMeal(request, response);
        } else if ("insertMeal".equals(command)) {
            insertMeal(request, response);
        } else if ("updateMeal".equals(command)) {
            updateMeal(request, response);
        } else {
            listMeal(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");

        if (command == null || command.isEmpty()) {
            listMeal(request, response);
        } else if ("deleteMeal".equals(command)) {
            deleteMeal(request, response);
        } else if ("showCreateForm".equals(command)) {
            showCreateForm(request, response);
        } else if ("updateForm".equals(command)) {
            showUpdateForm(request, response);
        } else {
            listMeal(request, response);
        }
    }


    private void listMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/meals.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LocalDateTime localDateTime = DateUtil.parseString(request.getParameter("date"), "yyyy-MM-dd'T'HH:mm");
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("caloriesNumber"));
            logger.debug("inserted new Meal object");
            mealDAO.insert(new Meal(mealDAO.generateId(), localDateTime, description, calories));
            response.sendRedirect(request.getContextPath() + "/meals");
        } catch (NumberFormatException e) {
            logger.error("negative argument - calories: ", e);
            request.setAttribute("caloriesNotNumberError", "В поле калории должно быть число");
            showCreateForm(request, response);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/mealForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("meal", mealDAO.getEntityByID(id));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/mealForm.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            logger.error("negative argument - id: ", e);
            response.sendRedirect(request.getContextPath() + "/meals");
        }
    }

    private void updateMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            LocalDateTime localDateTime = DateUtil.parseString(request.getParameter("date"), "yyyy-MM-dd'T'HH:mm");
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("caloriesNumber"));
            logger.debug("updated Meal object with id = " + id);
            mealDAO.update(new Meal(id, localDateTime, description, calories));
        } catch (NumberFormatException e) {
            logger.error("negative argument - calories: ", e);
            request.setAttribute("caloriesNotNumberError", "В поле калории должно быть число");
            showUpdateForm(request, response);
        }

        response.sendRedirect(request.getContextPath() + "/meals");
    }

    private void deleteMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.debug("deleted Meal object with id = " + id);
            mealDAO.delete(id);
        } catch (NumberFormatException e) {
            logger.error("negative argument - id: ", e);
        }

        response.sendRedirect(request.getContextPath() + "/meals");
    }
}
