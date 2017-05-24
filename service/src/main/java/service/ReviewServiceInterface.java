package service;

import entity.Film;
import service.exception.ServiceException;

import java.util.List;

/**
 * This interface defines special methods for ReviewService
 */
public interface ReviewServiceInterface<T> extends BaseServiceInterface<T> {
    /**
     * This method defines actions before saving entity from database
     * and then call appropriate dao method
     *
     * @param entity Object review for save
     * @param id     Id entity film
     * @param name   Username
     * @return
     */
    T saveForFilm(T entity, long id, String name) throws ServiceException;

    /**
     * This method defines actions before deleting Entity from database
     * and then call appropriate dao method
     *
     * @param id Id entity , which should be deleted
     */
    void deleteById(Long id) throws ServiceException;
}
