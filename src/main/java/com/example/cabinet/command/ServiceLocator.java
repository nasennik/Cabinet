package com.example.cabinet.command;

import javax.ejb.Local;

//@Local
public interface ServiceLocator {
    Command getCommand(String commandName);
}
