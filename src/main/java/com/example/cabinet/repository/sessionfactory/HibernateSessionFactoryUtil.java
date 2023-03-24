package com.example.cabinet.repository.sessionfactory;

import com.example.cabinet.entity.Book;
import com.example.cabinet.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.ejb.Stateless;

@Slf4j
@Stateless
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Book.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                log.error("Cannot run session factory", e);
                throw new RuntimeException("Cannot run session factory", e);
            }
        }
        return sessionFactory;
    }
}