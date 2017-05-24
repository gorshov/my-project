package service;

import service.exception.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * This interface defines special methods for StarService
 */
public interface StarServiceInterface<T> extends BaseServiceInterface<T> {
    /**
     * This method defines actions before deleting Entity from database
     * and then call appropriate dao method deleteByName
     *
     * @param name Name entity, which should be deleted
     */
    void deleteByName(String name) throws ServiceException;

    /**
     * This method defines actions before get Entity database
     * and then call appropriate dao method getFilmsForViewInParts
     *
     * @param pageNumber
     * @param recordPerPage
     * @return
     */
    Set<T> getStarsForViewInParts(int pageNumber, int recordPerPage) throws ServiceException;

    /**
     * Method returns total count of stars in database
     *
     * @return
     */
    long getCountForViewInParts() throws ServiceException;
}
