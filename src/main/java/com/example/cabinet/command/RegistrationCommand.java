package com.example.cabinet.command;

import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exeption.ServiceError;
import com.example.cabinet.service.PersonService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrationCommand implements Command{
    private final RequestFactory requestFactory;
    private final PersonService personService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String year = request.getParameter("year");
        if(personService.registration(name,year,login,password)){
            return requestFactory.createRedirectResponse("/Cabinet-1.0-SNAPSHOT/controller?command=main");
        }
        return requestFactory.createForwardResponse(PagePath.REGISTRATION_PAGE.getPath());
    }
}