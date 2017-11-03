package ru.javawebinar.topjava.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import ru.javawebinar.topjava.command.ActionCommand;
import ru.javawebinar.topjava.command.CommandFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(UserServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String page = null;

        CommandFactory commandFactory = new CommandFactory();
        ActionCommand command = commandFactory.defineCommand(request);

        page = command.execute(request);

        if (page != null) {
            switch (page) {
                case "deleted": { // this is to clear request when delete....
                    response.sendRedirect("/meals");
                    return;
                }
            }
            log.debug("Forward to " + page);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);

            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/meals");
        }
    }
}
