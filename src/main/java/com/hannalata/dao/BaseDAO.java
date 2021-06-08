package com.hannalata.dao;

import com.hannalata.factory.HibernateFactory;
import com.hannalata.model.BaseEntity;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;

@Getter
public abstract class BaseDAO<T extends BaseEntity> {

    protected final SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
    private Class<T> persistentClass;

    public BaseDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(T entity) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Integer id = (Integer) session.save(entity);
        session.getTransaction().commit();
        session.close();
        entity.setId(id);
        return entity;
    }

    public T update(T entity){
        if (entity.getId() == null) {
            return null;
        }
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    public T findOne(Integer id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        T entityFromDB = session.find(getPersistentClass(), id);
        session.getTransaction().commit();
        session.close();
        return entityFromDB;
    }

    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}