package com.example.cabinet.service;

import com.example.cabinet.entity.Person;
import com.example.cabinet.exeption.RepositoryException;
import com.example.cabinet.exeption.ServiceError;
import com.example.cabinet.repository.PersonRepository;
import com.example.cabinet.security.PasswordHasher;
import com.example.cabinet.validator.PersonValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class SimplePersonService implements PersonService {

    private final PersonValidator personValidator;

    private final PasswordHasher passwordHasher;

    private final PersonRepository personRepository;

    @Override
    public Optional<Person> login(String login, String password) {
        if (!personValidator.validate(login, password)) {
            log.error("invalid user login and password, cannot login");
            return Optional.empty();
        }
        try {
            List<Person> personList = personRepository.getAll();
            List<Person> person = personList.stream().filter(x -> x.getLogin().equals(login)).collect(Collectors.toList());
            if (person.size() > 0) {
                System.out.println(person.get(0));
                if (passwordHasher.checkIsEqualsPasswordAndPasswordHash(password, person.get(0).getPassword())) {
                    return Optional.of(person.get(0));
                }
            }

        } catch (RepositoryException e) {
            log.error("Cannot login user", e);
            throw new ServiceError("Cannot login user", e);
        }
        log.error("Cannot login user");
        return Optional.empty();
    }

    @Override
    public Optional<Person> getByLogin(String login) {
        if (!personValidator.validate(login)) {
            log.error("invalid user login and password, cannot login");
            return Optional.empty();
        }
        try {
            List<Person> personList = personRepository.getAll();
            List<Person> person = personList.stream().filter(x -> x.getLogin().equals(login)).collect(Collectors.toList());
            if (person.size() > 0) {
                return Optional.of(person.get(0));
            }
        } catch (RepositoryException e) {
            log.error("Cannot find person by login userLogin= " + login, e);
            throw new ServiceError("Cannot find person by login", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean registration(String name, String yearOfBirth, String login, String password) {
        if (!personValidator.validate(login, password)) {
            log.error("invalid user login and password, cannot login");
            return false;
        }
        try {
            int yearOfBirthAsInt = Integer.parseInt(yearOfBirth);
            Person person = Person.builder().
                    name(name).
                    yearOfBirth(yearOfBirthAsInt).
                    login(login).
                    password(passwordHasher.hashPassword(password)).
                    build();
            return personRepository.add(person);
        } catch (RepositoryException e) {
            log.error("Cannot reg user", e);
            throw new ServiceError("Cannot reg user", e);
        }
    }

    @Override
    public void update(String name, String yearOfBirth, String id, String login, String password) {
        if (!personValidator.validate(name, yearOfBirth, id)) {
            log.error("invalid user login and password, cannot login");
            return;
        }
        Person person = Person.builder().
                name(name).
                login(login).
                password(password).
                id(Integer.parseInt(id)).
                yearOfBirth(Integer.parseInt(yearOfBirth)).
                build();
        try{
            personRepository.update(person);
        }catch (RepositoryException e){
            log.error("Cannot update person",e);
            throw new ServiceError("Cannot update person",e);
        }
    }
}