package com.hannalata.dao;

import com.hannalata.factory.HibernateFactory;
import com.hannalata.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDAO {

    private static final SessionFactory sessionFactory = HibernateFactory.getSessionFactory();

    public static User save(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Integer id = (Integer) session.save(user);
        session.getTransaction().commit();
        session.close();
        if(id != null) {
            user.setId(id);
            return user;
        } else {
            return null;
        }
    }

    public static User update(User user) {
        if(user.getId() == null) {
            return null;
        } else {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
            session.close();
            return user;
        }

    }

    public static User findOne(Integer id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        User userFromDB = session.find(User.class, id);
        session.getTransaction().commit();
        session.close();
        return userFromDB;
    }

    public static List<User> findAll() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM users";
        List<User> result = session.createNativeQuery(sql).getResultList();
        session.close();
        return result;
    }

    public static void delete(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }
}
