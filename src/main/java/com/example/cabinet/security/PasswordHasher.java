package com.example.cabinet.security;

public interface PasswordHasher {

    boolean checkIsEqualsPasswordAndPasswordHash(String password, String passwordHash);

    String hashPassword(String password);
}
