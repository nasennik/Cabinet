package com.example.cabinet.command;

public enum CommandURL {
    LOGIN_URL("/Cabinet-1.0-SNAPSHOT/controller?command=login"),
    CABINET_URL("/Cabinet-1.0-SNAPSHOT/controller?command=cab"),
    ALL_URL("/Cabinet-1.0-SNAPSHOT/controller?command=allPeople"),
    ERROR_URL("/Cabinet-1.0-SNAPSHOT/controller?command=error");

    private final String URL;

     CommandURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

}
