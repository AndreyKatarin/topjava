package ru.javawebinar.topjava.command;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.UserServlet;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by admin on 03.11.2017.
 */
public class IndexCommand  implements ActionCommand {

    private static final Logger log = getLogger(IndexCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        log.debug("set List of meals as an attribute to request");
        request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, 2000));

        return null;
    }
}
