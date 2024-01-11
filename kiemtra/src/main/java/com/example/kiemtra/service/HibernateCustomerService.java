package com.example.kiemtra.service;

import com.example.kiemtra.model.City;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class HibernateCustomerService implements ICustomerService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {
        String queryStr = "SELECT c FROM City AS c";
        TypedQuery<City> query = entityManager.createQuery(queryStr, City.class);
        return query.getResultList();
    }

    @Override
    public void save(City city) {
        Transaction transaction = null;
        City origin;
        if (city.getId() == 0) {
            origin = new City();
        } else {
            origin = findById(city.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setNameCity(city.getNameCity());
            origin.setNational(city.getNameCity());

            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public City findById(Long id) {
        String queryStr = "SELECT c FROM City AS c WHERE c.id = :id";
        TypedQuery<City> query = entityManager.createQuery(queryStr, City.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(Long id) {
        City city = findById(id);
        if (city != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(city);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
