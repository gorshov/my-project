package dao;

import java.io.Serializable;
import java.util.List;

/**
 * This is base interface of Data Access Object class, which defines common behavior of all Dao classes.
 *
 * @param <T>
 */
public interface BaseDaoInterface<T> {
    /**
     * This method save or update entity in database
     *
     * @param entity
     * @return
     */
    T saveOrUpdate(T entity);

    /**
     * This method get entity from database by Id
     *
     * @param clazz
     * @param id
     * @return
     */
    T getById(Class clazz, Serializable id);

    /**
     * This method get all entity from database
     *pri
     * @param clazz
     * @return
     */
    List<T> getAll(Class clazz);

    /**
     * This method update entity in database
     *
     * @param entity
     */
    void update(T entity);

    /**
     * This method delete entity from database
     *
     * @param t
     */
    void delete(T t);

}
