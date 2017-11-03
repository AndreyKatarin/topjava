package ru.javawebinar.topjava.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 03.11.2017.
 */
public interface ActionCommand {

    String execute(HttpServletRequest request);
}
