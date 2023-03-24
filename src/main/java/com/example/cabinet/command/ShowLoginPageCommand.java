package com.example.cabinet.command;

import com.example.cabinet.controller.SimpleRequestFactory;
import com.example.cabinet.exception.ServiceError;
import lombok.Data;

import javax.inject.Inject;


@Data
public class ShowLoginPageCommand implements Command {

    public ShowLoginPageCommand(){
        System.out.println("ShowLoginPageCommand was created");
    }

    @Inject
    private SimpleRequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        System.out.println("Request factory is" + requestFactory);
        return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
    }
}
