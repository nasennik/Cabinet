package com.example.cabinet.command;

public interface CommandResponse {
    boolean isRedirect();

    String getPath();
}
