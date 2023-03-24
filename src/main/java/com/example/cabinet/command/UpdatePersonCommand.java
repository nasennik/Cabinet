package com.example.cabinet.command;


import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exception.ServiceError;
import com.example.cabinet.service.PersonService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor
public class UpdatePersonCommand implements Command {
    @Inject
    private RequestFactory requestFactory;
    @Inject
    private PersonService personService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String name = request.getParameter("name");
        String year = request.getParameter("year");
        String id = request.getParameter("id");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        personService.update(name, year, id, login, password);
        return requestFactory.createRedirectResponse("/Cabinet-1.0-SNAPSHOT/controller?command=cab");
    }
}