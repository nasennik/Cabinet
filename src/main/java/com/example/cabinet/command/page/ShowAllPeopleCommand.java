package com.example.cabinet.command.page;

import com.example.cabinet.command.Command;
import com.example.cabinet.command.CommandRequest;
import com.example.cabinet.command.CommandResponse;
import com.example.cabinet.command.CommandURL;
import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exception.ServiceError;
import com.example.cabinet.service.PersonService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor
public class ShowAllPeopleCommand implements Command {
    @Inject
    private RequestFactory requestFactory;
    @Inject
    private PersonService personService;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        if (request.sessionExists() && request.retrieveFromSession("person").isPresent()) {

            request.addAttributeToJsp("allPeople",personService.showAll());
            return requestFactory.createForwardResponse(PagePath.ALL_PAGE.getPath());
        }
        return requestFactory.createRedirectResponse(CommandURL.CABINET_URL.getURL());
    }
}
