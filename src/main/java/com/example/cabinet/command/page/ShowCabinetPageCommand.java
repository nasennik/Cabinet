package com.example.cabinet.command.page;

import com.example.cabinet.command.Command;
import com.example.cabinet.command.CommandRequest;
import com.example.cabinet.command.CommandResponse;
import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.entity.Person;
import com.example.cabinet.exception.ServiceError;
import com.example.cabinet.service.PersonService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.Optional;

@RequiredArgsConstructor
public class ShowCabinetPageCommand implements Command {
    @Inject
    private  RequestFactory requestFactory;
    @Inject
    private PersonService personService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        if (request.sessionExists() && request.retrieveFromSession("person").isPresent()) {
            Person person = (Person) request.retrieveFromSession("person").get();
            Optional<Person> personOptional = personService.getByLogin(person.getLogin());
            if (personOptional.isPresent()) {
                request.addAttributeToJsp("person", personOptional.get());
            }
            return requestFactory.createForwardResponse(PagePath.CABINET_PAGE.getPath());
        }
        return requestFactory.createRedirectResponse("/Cabinet-1.0-SNAPSHOT/controller?command=login");
    }
}
