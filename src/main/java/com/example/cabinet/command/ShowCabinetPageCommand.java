package com.example.cabinet.command;

import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.entity.Person;
import com.example.cabinet.exeption.ServiceError;
import com.example.cabinet.service.PersonService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

    @RequiredArgsConstructor
    public class ShowCabinetPageCommand implements Command {
        private final RequestFactory requestFactory;
        private final PersonService personService;
        @Override
        public CommandResponse execute(CommandRequest request) throws ServiceError {
            if (request.sessionExists() && request.retrieveFromSession("person").isPresent()) {
                Person person =(Person)request.retrieveFromSession("person").get();
                Optional<Person> personOptional = personService.getByLogin(person.getLogin());
                if(personOptional.isPresent()){
                    request.addAttributeToJsp("person",personOptional.get());
                }
                return requestFactory.createForwardResponse(PagePath.CABINET_PAGE.getPath());
            }
            return requestFactory.createRedirectResponse("/Cabinet-1.0-SNAPSHOT/controller?command=login");
        }
}
