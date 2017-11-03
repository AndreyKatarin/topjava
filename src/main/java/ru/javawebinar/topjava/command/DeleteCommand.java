package ru.javawebinar.topjava.command;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class DeleteCommand implements ActionCommand {

    private static final Logger log = getLogger(DeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));

        MealDAO mealDAO = new MealDAO();
        log.debug("delete meal entry with id = " + id);
        mealDAO.delete(mealDAO.getEntityByID(id));

        List<Meal> meals = mealDAO.getAll();
        request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));

        return "deleted";
    }
}
