package com.example.cabinet.service;

import com.example.cabinet.entity.Person;
import com.example.cabinet.exception.RepositoryException;
import com.example.cabinet.repository.PersonRepository;
import com.example.cabinet.security.PasswordHasher;
import com.example.cabinet.validator.PersonValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimplePersonServiceTest {
    @Mock
    private PersonValidator personValidator;

    @Mock
    private PasswordHasher passwordHasher;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private SimplePersonService personService;

    @Test
    public void testLogin() throws Exception {
        String login = "testuser";
        String password = "testpassword!";
        Person person = Person.builder()
                .name("Test User")
                .login(login)
                .password(passwordHasher.hashPassword(password))
                .build();
        List<Person> personList = Collections.singletonList(person);
        when(personValidator.validate(login, password)).thenReturn(true);
        when(personRepository.getAll()).thenReturn(personList);
        when(passwordHasher.checkIsEqualsPasswordAndPasswordHash(password, person.getPassword())).thenReturn(true);

        Optional<Person> result = personService.login(login, password);

        assertTrue(result.isPresent());
        assertEquals(person, result.get());
    }
    @Test
    public void testGetByLogin() throws Exception {

        String login = "testuser";
        Person person = Person.builder()
                .name("Test User")
                .login(login)
                .build();
        List<Person> personList = Collections.singletonList(person);
        when(personValidator.validate(login)).thenReturn(true);
        when(personRepository.getAll()).thenReturn(personList);

        Optional<Person> result = personService.getByLogin(login);

        assertTrue(result.isPresent());
        assertEquals(person, result.get());
    }

    @Test
    public void testUpdate() throws Exception {
        String name = "Test User";
        String yearOfBirth = "1980";
        String id = "1";
        String login = "testuser";
        String password = "testpassword";
        Person person = Person.builder()
                .name(name)
                .yearOfBirth(Integer.parseInt(yearOfBirth))
                .password(password)
                .login(login)
                .id(Integer.parseInt(id))
                .build();
        when(personValidator.validate(name, yearOfBirth)).thenReturn(true);

        personService.update(name, yearOfBirth, id, login, password);

        verify(personRepository).update(person);
    }

    @Test
    public void testRegistration() throws RepositoryException {
        // Arrange
        String name = "John Doe";
        String yearOfBirth = "1990";
        String login = "johndoe";
        String password = "password123";

        when(personValidator.validate(login, password)).thenReturn(true);
        when(passwordHasher.hashPassword(password)).thenReturn("hashed_password");
        when(personRepository.add(any(Person.class))).thenReturn(true);

        boolean result = personService.registration(name, yearOfBirth, login, password);

        assertTrue(result);
        verify(personValidator).validate(login, password);
        verify(passwordHasher).hashPassword(password);
        verify(personRepository).add(Person.builder()
                .name(name)
                .yearOfBirth(Integer.parseInt(yearOfBirth))
                .login(login)
                .password("hashed_password")
                .build());
    }

    @Test
    public void testRegistrationInvalidLoginPassword() {
        String name = "John Doe";
        String yearOfBirth = "1990";
        String login = "johndoe";
        String password = "password123";

        when(personValidator.validate(login, password)).thenReturn(false);

        boolean result = personService.registration(name, yearOfBirth, login, password);

        assertFalse(result);
        verify(personValidator).validate(login, password);
    }
}
