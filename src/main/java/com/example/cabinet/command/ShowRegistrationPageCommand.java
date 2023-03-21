package com.example.cabinet.command;

import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exeption.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowRegistrationPageCommand implements Command{
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {

        return requestFactory.createForwardResponse(PagePath.REGISTRATION_PAGE.getPath());
    }
}
