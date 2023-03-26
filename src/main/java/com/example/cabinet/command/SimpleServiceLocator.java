package com.example.cabinet.command;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@Slf4j
public class SimpleServiceLocator implements ServiceLocator {
    @Inject
    private Cache CACHE;
    @Inject
    private InitialContext initialContext;

    @Override
    public Command getCommand(String commandName) {

      final Optional<Command> commandFromCache = CACHE.getCommand(commandName);
        if (commandName == null) {
            commandName = "login";
        }
        if (commandFromCache.isPresent()) {
            return commandFromCache.get();
        }

        final Command command = initialContext.lookup(commandName);
        CACHE.addCommand(commandName, command);
        log.info("Get command in Service Locator from Cache");
        return command;
    }
}