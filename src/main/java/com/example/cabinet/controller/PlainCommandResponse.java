package com.example.cabinet.controller;


import com.example.cabinet.command.CommandResponse;
import lombok.Data;

import java.util.Objects;
@Data
public class PlainCommandResponse implements CommandResponse {
    private final boolean redirect;
    private final String path;

    public PlainCommandResponse(String path) {
        this(false, path);
    }

    public PlainCommandResponse(boolean redirect, String path) {
        this.redirect = redirect;
        this.path = path;
    }

    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public String getPath() {
        return path;
    }

}