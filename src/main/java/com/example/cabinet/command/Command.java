package com.example.cabinet.command;

import com.example.cabinet.exeption.ServiceError;

public interface Command {
    CommandResponse execute(CommandRequest request) throws ServiceError;
}
