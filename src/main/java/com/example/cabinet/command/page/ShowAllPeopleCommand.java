package com.example.cabinet.command.page;

import com.example.cabinet.command.Command;
import com.example.cabinet.command.CommandRequest;
import com.example.cabinet.command.CommandResponse;
import com.example.cabinet.command.CommandURL;
import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.entity.Person;
import com.example.cabinet.exception.RepositoryException;
import com.example.cabinet.exception.ServiceError;
import com.example.cabinet.service.PersonService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShowAllPeopleCommand implements Command {
    @Inject
    private RequestFactory requestFactory;
    @Inject
    private PersonService personService;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        if (request.sessionExists() && request.retrieveFromSession("person").isPresent()) {
            List<Person> personList = null;
            try {
                personList = personService.showAll();
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
            request.addAttributeToJsp("allPeople", personList);
            return requestFactory.createForwardResponse(PagePath.ALL_PAGE.getPath());
        }
        return requestFactory.createRedirectResponse(CommandURL.CABINET_URL.getURL());
    }
}
