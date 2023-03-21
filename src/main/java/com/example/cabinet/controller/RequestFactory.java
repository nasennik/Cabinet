package com.example.cabinet.controller;


import com.example.cabinet.command.CommandRequest;
import com.example.cabinet.command.CommandResponse;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
@Local
public interface RequestFactory {

    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponse(String path);

}