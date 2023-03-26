package com.example.cabinet.repository;

import com.example.cabinet.entity.Person;
import com.example.cabinet.exception.RepositoryException;

import java.util.List;
import java.util.Optional;


public interface PersonRepository {
    Optional<Person> getById(int id) throws RepositoryException;

    boolean add(Person person) throws RepositoryException;

    void update(Person person) throws RepositoryException;

    List<Person> getAll() throws RepositoryException;

    Optional<Person> getByLogin(String login) throws RepositoryException;
}
