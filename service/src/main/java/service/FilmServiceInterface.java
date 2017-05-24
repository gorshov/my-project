package service;

import service.exception.ServiceException;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This interface defines special methods for FilmService
 */
public interface FilmServiceInterface<T> extends BaseServiceInterface<T> {
    /**
     * This method defines actions before deleting Entity from database
     * and then call appropriate dao method deleteById
     *
     * @param id Id entity , which should be deleted
     */
    void deleteById(Long id) throws ServiceException;

    /**
     * This method defines actions before get Entity database
     * and then call appropriate dao method
     *
     * @param pageNumber
     * @param recordPerPage
     * @return
     */
    Set<T> getFilmsForViewInParts(int pageNumber, int recordPerPage) throws ServiceException;

    /**
     * Method returns total count of films in database
     *
     * @return
     */
    long getCountForViewInParts() throws ServiceException;

    /**
     * This method search film by release data
     *
     * @param year release data
     * @return
     */
    List<T> getFilmsByYear(String year) throws ServiceException;

    /**
     * This method search film by title;
     *
     * @param title
     * @return
     */
    List<T> findByTitle(String title) throws ServiceException;
}
