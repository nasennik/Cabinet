package com.example.cabinet.command;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j

@Singleton
public class Cache {
    private final Map<String, Command> commandCache = new ConcurrentHashMap<>();

    public Optional<Command> getCommand(String commandName) {
        log.info("Get command by name from cache");
        if(commandName!=null){
            return Optional.ofNullable(commandCache.get(commandName));
        }
        return Optional.empty();
    }
    public void addCommand(String commandName, Command command) {
        log.info("Add command into cache");
        commandCache.put(commandName, command);
    }
}
