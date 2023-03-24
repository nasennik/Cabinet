package com.example.cabinet.command;


import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exception.ServiceError;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor
public class LogoutCommand implements Command {
    @Inject
    private RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        if (request.sessionExists()) {
            request.clearSession();
        }
        return requestFactory.createRedirectResponse("/Cabinet-1.0-SNAPSHOT/controller?command=login");
    }
}