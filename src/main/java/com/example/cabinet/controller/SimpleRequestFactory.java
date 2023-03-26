package com.example.cabinet.controller;

import com.example.cabinet.command.CommandRequest;
import com.example.cabinet.command.CommandResponse;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;


@Singleton
public class SimpleRequestFactory implements RequestFactory {
    @Override
    public CommandRequest createRequest(HttpServletRequest request) {
        return new WrappingCommandRequest(request);
    }

    @Override
    public CommandResponse createForwardResponse(String path) {
        return new PlainCommandResponse(path);
    }

    @Override
    public CommandResponse createRedirectResponse(String path) {
        return new PlainCommandResponse(true, path);
    }
}