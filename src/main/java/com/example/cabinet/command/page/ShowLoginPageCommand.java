package com.example.cabinet.command.page;

import com.example.cabinet.command.Command;
import com.example.cabinet.command.CommandRequest;
import com.example.cabinet.command.CommandResponse;
import com.example.cabinet.command.page.PagePath;
import com.example.cabinet.controller.SimpleRequestFactory;
import com.example.cabinet.exception.ServiceError;
import lombok.Data;

import javax.inject.Inject;


@Data
public class ShowLoginPageCommand implements Command {
    @Inject
    private SimpleRequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
    }
}
