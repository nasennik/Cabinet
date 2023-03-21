package com.example.cabinet.command;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Optional;
@Singleton
@LocalBean
public class SimpleServiceLocator implements ServiceLocator {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleServiceLocator.class);
   @EJB
    private Cache CACHE; //= new Cache();

    @Override
    public Command getCommand(String commandName) {
        final Optional<Command> commandFromCache = CACHE.getCommand(commandName);
        if (commandName == null) {
            commandName = "mainPage";
        }
        if (commandFromCache.isPresent()) {
            return commandFromCache.get();
        }

        final InitialContext initialContext = new InitialContext();

        final Command command = initialContext.lookup(commandName);
        CACHE.addCommand(commandName, command);
        LOG.info("Get command in Service Locator from Cache");
        return command;
    }
}