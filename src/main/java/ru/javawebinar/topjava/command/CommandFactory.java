package ru.javawebinar.topjava.command;

import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static org.slf4j.LoggerFactory.getLogger;


public class CommandFactory {

    private static final Logger log = getLogger(CommandFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new IndexCommand();

        String action = request.getParameter("command");

        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch(IllegalArgumentException e) {
            request.setAttribute("wrongCommand", action + " не поддерживается!");
            log.error("negative argument: ", e);
        }

        log.info("Current command is " + action);
        return current;
    }
}
