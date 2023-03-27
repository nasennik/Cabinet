package com.example.cabinet.command;

import com.example.cabinet.command.page.PagePath;
import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.entity.Person;
import com.example.cabinet.exception.ServiceError;
import com.example.cabinet.service.PersonService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.Optional;

@RequiredArgsConstructor

public class LoginCommand implements Command {
    @Inject
    private RequestFactory requestFactory;
    @Inject
    private PersonService personService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        if (request.sessionExists() && request.retrieveFromSession("person").isPresent()) {
            return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<Person> personOptional = personService.login(login, password);
        if (personOptional.isPresent()) {
            request.clearSession();
            request.createSession();
            request.addToSession("person", personOptional.get());
            return requestFactory.createRedirectResponse(CommandURL.CABINET_URL.getURL());
        }
        request.addAttributeToJsp("errorLoginPassMessage", "invalid login or password");
        return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
    }
}