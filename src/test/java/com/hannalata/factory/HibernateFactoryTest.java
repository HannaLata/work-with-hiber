package com.hannalata.factory;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HibernateFactoryTest {

    @Test
    void getSessionFactory() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        assertNotNull(sessionFactory);
    }
}