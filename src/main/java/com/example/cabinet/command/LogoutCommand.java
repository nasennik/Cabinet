package com.example.cabinet.command;


import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exeption.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutCommand implements Command{
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        if(request.sessionExists()){
            request.clearSession();
        }
        return requestFactory.createRedirectResponse("/Cabinet-1.0-SNAPSHOT/controller?command=login");
    }
}