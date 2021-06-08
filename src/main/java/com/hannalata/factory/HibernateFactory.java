package com.hannalata.factory;

import com.hannalata.model.Cart;
import com.hannalata.model.Item;
import com.hannalata.model.Order;
import com.hannalata.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateFactory {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
//                Configuration configuration = new Configuration().configure();
//                sessionFactory = configuration.buildSessionFactory();

                Configuration configuration = new Configuration();

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                configuration.addAnnotatedClass(Item.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Cart.class);

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
