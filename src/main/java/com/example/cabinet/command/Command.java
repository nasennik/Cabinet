package com.example.cabinet.command;

import com.example.cabinet.exception.RepositoryException;
import com.example.cabinet.exception.ServiceError;


public interface Command {
    CommandResponse execute(CommandRequest request) throws ServiceError;
}
