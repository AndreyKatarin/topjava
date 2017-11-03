package ru.javawebinar.topjava.command;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class UpdateCommand implements ActionCommand {

    private static final Logger log = getLogger(UpdateCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LocalDateTime localDateTime = DateUtil.parseString(request.getParameter("date"), "yyyy-MM-dd'T'HH:mm");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("caloriesNumber"));
        int id = Integer.parseInt(request.getParameter("id"));

        MealDAO mealDAO = new MealDAO();
        log.debug("updated meal entry id = " + id);
        mealDAO.update(new Meal(id, localDateTime, description, calories));

        List<Meal> meals = mealDAO.getAll();

        request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, 2000));

        return "/jsps/meals.jsp";
    }
}
