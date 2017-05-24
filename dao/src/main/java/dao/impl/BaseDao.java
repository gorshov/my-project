package dao.impl;

import dao.BaseDaoInterface;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Александр Горшов on 20.05.2017.
 * This base dao class.
 * This class is responsible for realizing common methods for all DAO (CRUD methods).
 */
@Repository
@Primary
public class BaseDao<T> implements BaseDaoInterface<T> {
    private static Logger log = Logger.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public T saveOrUpdate(T entity) {
        log.info("Start saveOrUpdate method : " + entity);
        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public T getById(Class clazz, Serializable id) {
        log.info("Get " + clazz + " " + id);
        return (T) getSession().get(clazz, id);
    }

    @Override
    /*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)*/
    public List<T> getAll(Class clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        List result = criteria.list();
        return result;
    }

    @Override
    public void update(T entity) {
        log.info("Start update method : " + entity);
        getSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        log.info("Start delete method : " + entity);
        getSession().delete(entity);
    }

}
