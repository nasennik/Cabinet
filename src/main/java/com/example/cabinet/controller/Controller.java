package com.example.cabinet.controller;


import com.example.cabinet.command.Command;
import com.example.cabinet.command.CommandRequest;
import com.example.cabinet.command.CommandResponse;
import com.example.cabinet.command.SimpleServiceLocator;
import com.example.cabinet.exception.ServiceError;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/controller")
@Slf4j
public class Controller extends HttpServlet {
    private static final String COMMAND_NAME_PARAM = "command";
    @Inject
    private SimpleRequestFactory requestFactory;
    @Inject
    private SimpleServiceLocator serviceLocator;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceError {
        log.trace("caught req and resp in doGet method");
        try {
            processRequest(req, resp);
        } catch (ServiceError | IOException | ServletException e) {
            log.error("Exception in doGet method servlet", e);
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceError {

        log.trace("caught req and resp in doPost method");
        try {
            processRequest(req, resp);
        } catch (ServiceError | IOException | ServletException e) {
            log.error("Service exception in doGet method servlet", e);
            throw e;
        }
    }

    private void processRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServiceError, IOException, ServletException {
        final String commandName = httpRequest.getParameter(COMMAND_NAME_PARAM);
        final Command command = serviceLocator.getCommand(commandName);
        final CommandRequest commandRequest = requestFactory.createRequest(httpRequest);
        final CommandResponse commandResponse = command.execute(commandRequest);
        proceedWithResponse(httpRequest, httpResponse, commandResponse);
    }

    private void proceedWithResponse(HttpServletRequest req, HttpServletResponse resp,
                                     CommandResponse commandResponse) throws IOException, ServletException {

        forwardOrRedirectToResponseLocation(req, resp, commandResponse);
    }

    private void forwardOrRedirectToResponseLocation(HttpServletRequest req, HttpServletResponse resp,
                                                     CommandResponse commandResponse) throws IOException, ServletException {
        if (commandResponse.isRedirect()) {
            resp.sendRedirect(commandResponse.getPath());
        } else {
            final String desiredPath = commandResponse.getPath();
            final RequestDispatcher dispatcher = req.getRequestDispatcher(desiredPath);
            dispatcher.forward(req, resp);
        }
    }
}