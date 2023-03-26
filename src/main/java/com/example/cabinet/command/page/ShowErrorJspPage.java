package com.example.cabinet.command.page;

import com.example.cabinet.command.Command;
import com.example.cabinet.command.CommandRequest;
import com.example.cabinet.command.CommandResponse;
import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exception.ServiceError;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor
public class ShowErrorJspPage implements Command {
    @Inject
    private RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.ERROR_JSP_PAGE.getPath());
    }
}