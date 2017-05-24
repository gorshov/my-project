package service;

import java.io.Serializable;
import java.util.List;

/**
 * This is base interface of all Services, which defines common methods for all Services(CRUD methods)
 *
 * @param <T>
 */
public interface BaseServiceInterface<T> {
    /**
     * This method defines actions before saving or updating Entity
     * and then call appropriate dao method
     *
     * @param entity for saving or updating
     * @return saving Object
     */
    T saveOrUpdate(T entity);

    /**
     * This method defines actions before getting entity from database
     * and then call appropriate dao method
     *
     * @param clazz Entity for search
     * @param id    Serializable Entity id
     * @return Object of Entity class
     */
    T getById(Class clazz, Serializable id);

    /**
     * This method defines class and id before getting entity from database
     * and then call appropriate dao method
     *
     * @param clazz Entity for search
     * @return All bject of Entity class
     */
    List<T> getAll(Class clazz);

}
