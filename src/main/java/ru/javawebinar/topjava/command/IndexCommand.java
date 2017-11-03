package ru.javawebinar.topjava.command;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class IndexCommand  implements ActionCommand {

    private static final Logger log = getLogger(IndexCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        MealDAO mealDAO = new MealDAO();

        List<Meal> meals = mealDAO.getAll();

        log.debug("set list of meals as an attribute to request");
        request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, 2000));

        return "/jsps/meals.jsp";
    }
}
