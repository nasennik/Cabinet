package com.example.cabinet.command;
import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.entity.Person;
import com.example.cabinet.exeption.ServiceError;
import com.example.cabinet.service.PersonService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class LoginCommand implements Command{
    private final RequestFactory requestFactory;
    private final PersonService personService;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        if (request.sessionExists() && request.retrieveFromSession("person").isPresent()) {
            return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<Person> personOptional = personService.login(login,password);
        if(personOptional.isPresent()){
            request.clearSession();
            request.createSession();
            request.addToSession("person",personOptional.get());
            return requestFactory.createRedirectResponse("/Cabinet-1.0-SNAPSHOT/controller?command=cab");
        }
        request.addAttributeToJsp("errorLoginPassMessage","invalid login or password");
        return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
    }
}