package dao;

import java.io.Serializable;
import java.util.List;

/**
 * This interface defines special methods for working with the entity Star
 */
public interface StarDaoInterface<T> extends BaseDaoInterface<T> {
    /**
     * This method search film in which there is an star with a name
     *
     * @param lastName
     * @return
     */
    List<T> getFilmByActor(String lastName);

    /**
     * This method delete star by name
     *
     * @param name
     */
    void deleteByName(String name);

    /**
     * This method delete star by id
     *
     * @param id
     */
    void deleteById(Serializable id);

    /**
     * Method returns list of restricted amount of stars for pagination
     *
     * @param pageNumber
     * @param recordPerPage
     * @return
     */
    List<T> getStarsForViewInParts(int pageNumber, int recordPerPage);

    /**
     * Method returns total amount of Star
     *
     * @return
     */
    long getCountForViewInParts();
}
