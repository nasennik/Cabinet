package com.example.cabinet.command;

import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exeption.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowErrorJspPage implements Command{
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.ERROR_JSP_PAGE.getPath());
    }
}