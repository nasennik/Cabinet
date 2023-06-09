package com.example.cabinet.service;
import com.example.cabinet.entity.Person;
import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> showAll();
    Optional<Person> login(String login, String password);

    boolean registration(String name, String yearOfBirth, String login, String password);

    Optional<Person> getByLogin(String login);

    void update(String name, String yearOfBirth, String id, String login, String password);
}