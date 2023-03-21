package com.example.cabinet.command;

import com.example.cabinet.controller.SimpleRequestFactory;
import com.example.cabinet.exeption.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowLoginPageCommand implements Command{
    private final SimpleRequestFactory requestFactory;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
    }
}
