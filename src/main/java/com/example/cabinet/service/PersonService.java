package com.example.cabinet.service;


import com.example.cabinet.entity.Person;

import java.util.Optional;

    public interface PersonService {
    Optional<Person> login(String login, String password);

    boolean registration(String name, String yearOfBirth, String login, String password);


    Optional<Person> getByLogin(String login);

    void update(String name, String yearOfBirth, String id, String login, String password);
}