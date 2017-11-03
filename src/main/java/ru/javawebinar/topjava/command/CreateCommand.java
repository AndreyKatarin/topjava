package ru.javawebinar.topjava.command;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class CreateCommand implements ActionCommand {

    private static final Logger log = getLogger(CreateCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LocalDateTime localDateTime = DateUtil.parseString(request.getParameter("date"), "yyyy-MM-dd'T'HH:mm");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("caloriesNumber"));

        MealDAO mealDAO = new MealDAO();
        log.debug("Insert new Meal Entry");
        mealDAO.insert(new Meal(mealDAO.getGeneratedId(), localDateTime, description, calories));

        request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));

        return "/jsps/meals.jsp";
    }
}
