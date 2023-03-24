package com.example.cabinet.command;

import com.example.cabinet.controller.SimpleRequestFactory;
import com.example.cabinet.repository.PersonRepository;
import com.example.cabinet.repository.sessionfactory.HibernateSessionFactoryUtil;
import com.example.cabinet.security.PasswordHasher;
import com.example.cabinet.service.PersonService;
import com.example.cabinet.validator.PersonValidator;
import jdk.jshell.spi.SPIResolutionException;
import lombok.Data;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Data
public class InitialContext {

    public InitialContext(){
        System.out.println("Initial context was created");
    }

    @Inject
    private SimpleRequestFactory simpleRequestFactory;// = new SimpleRequestFactory();

    @Inject
    private HibernateSessionFactoryUtil hibernateSessionFactoryUtil;

    @Inject
    private PersonRepository personRepository;

    @Inject
    private PasswordHasher passwordHasher;

    @Inject
    private PersonValidator personValidator;

    @Inject
    private PersonService personService;

    @Inject
    private ShowLoginPageCommand showLoginPageCommand;

    @Inject
    private ShowRegistrationPageCommand registrationPageCommand;

    @Inject
    private LoginCommand loginCommand;
    @Inject
    private RegistrationCommand registrationCommand;

    @Inject
    private LogoutCommand logoutCommand;

    @Inject
    private ShowCabinetPageCommand showCabinetPageCommand;
    @Inject
    private ShowErrorJspPage showErrorJspPage;

    @Inject
    private UpdatePersonCommand updatePersonCommand;




    public Command lookup(String commandName) {

        switch (commandName) {
            case "login": {
                return showLoginPageCommand;
            }
            case "registration": {
                return registrationPageCommand;
            }
            case "logincmnd": {
                return loginCommand;
            }
            case "registrationcmnd": {
                return registrationCommand;
            }
            case "logout": {
                return logoutCommand;
            }
            case "cab": {
                return showCabinetPageCommand;
            }
            case "error": {
                return showErrorJspPage;
            }
            case "updatePerson": {
                return updatePersonCommand;
            }
            default: {
                return showLoginPageCommand;
            }
        }
    }
}