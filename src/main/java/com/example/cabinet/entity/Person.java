package com.example.cabinet.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

}