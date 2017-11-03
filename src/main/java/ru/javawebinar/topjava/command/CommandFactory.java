package ru.javawebinar.topjava.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 03.11.2017.
 */
public class CommandFactory {

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new IndexCommand();

        String action = request.getParameter("command");

        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(request.getParameter("command"));
            current = currentEnum.getCurrentCommand();
        } catch(IllegalArgumentException e) {
            request.setAttribute("wrongCommand", action + " не поддерживается!");
        }

        return current;
    }
}
