package com.example.cabinet.command;

import com.example.cabinet.controller.RequestFactory;
import com.example.cabinet.exception.ServiceError;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor
@Data
public class ShowRegistrationPageCommand implements Command {
    @Inject
    private RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {

        return requestFactory.createForwardResponse(PagePath.REGISTRATION_PAGE.getPath());
    }
}
