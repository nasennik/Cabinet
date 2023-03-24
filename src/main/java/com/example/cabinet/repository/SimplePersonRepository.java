package com.example.cabinet.repository;

import com.example.cabinet.entity.Person;
import com.example.cabinet.exception.RepositoryException;
import com.example.cabinet.repository.sessionfactory.HibernateSessionFactoryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Stateless
public class SimplePersonRepository implements PersonRepository {

    @Inject
    private HibernateSessionFactoryUtil hibernateSessionFactoryUtil;

    @Override
    public Optional<Person> getById(int id) throws RepositoryException {
        try (Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.of(session.get(Person.class, id));
        } catch (HibernateException e) {
            log.error("Cannot get by id person", e);
            throw new RepositoryException("Cannot get by id person", e);
        }
    }

    @Override
    public boolean add(Person person) throws RepositoryException {
        try (Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.save(person);
            return true;
        } catch (HibernateException e) {
            log.error("Cannot save person", e);
            throw new RepositoryException("Cannot save person", e);
        }
    }

    @Override
    public void update(Person person) throws RepositoryException {
        try (Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(person);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Cannot update person", e);
            throw new RepositoryException("Cannot update person", e);
        }
    }

    @Override
    public List<Person> getAll() throws RepositoryException {
        try (Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return (List<Person>) session.createQuery("from Person ").list();

        } catch (HibernateException e) {
            log.error("Cannot get all persons", e);
            throw new RepositoryException("Cannot get all persons", e);
        }
    }

    @Override
    public Optional<Person> getByLogin(String login) throws RepositoryException {
        try (Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Person> query = session.createQuery("select id, name , yearOfBirth, login, password from Person  where login = :userlogin");
            query.setParameter("userlogin", login);
            List<Person> people = query.getResultList();

            return Optional.empty();
        } catch (HibernateException e) {
            log.error("Cannot get all persons", e);
            throw new RepositoryException("Cannot get all persons", e);
        }
    }
}