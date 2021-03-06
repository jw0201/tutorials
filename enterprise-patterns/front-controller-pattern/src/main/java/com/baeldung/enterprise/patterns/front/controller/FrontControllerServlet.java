package com.baeldung.enterprise.patterns.front.controller;

import com.baeldung.enterprise.patterns.front.controller.commands.FrontCommand;
import com.baeldung.enterprise.patterns.front.controller.commands.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response
    ) throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            return (FrontCommand) getCommandClass(request)
              .asSubclass(FrontCommand.class)
              .newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get command!", e);
        }
    }

    private Class getCommandClass(HttpServletRequest request) {
        try {
            return Class.forName(
              String.format(
                "com.baeldung.enterprise.patterns.front.controller.commands.%sCommand",
                request.getParameter("command")
              )
            );
        } catch (ClassNotFoundException e) {
            return UnknownCommand.class;
        }
    }
}
