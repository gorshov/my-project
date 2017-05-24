package dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;

/**
 * This interface defines special methods for working with the entity Film
 */
public interface FilmDaoInterface<T> extends BaseDaoInterface<T> {
    /**
     * This method search film by release data
     *
     * @param year
     * @return
     */
    List<T> getFilmsByYear(String year);

    /**
     * This method delete film by id
     *
     * @param id
     */
    void deleteById(Serializable id);

    /**
     * Method returns list of restricted amount of films for pagination
     *
     * @param pageNumber
     * @param recordPerPage
     * @return
     */
    List<T> getFilmsForViewInParts(int pageNumber, int recordPerPage);

    /**
     * Method returns total amount of films
     *
     * @return
     */
    long getCountForViewInParts();

    /**
     * Method finds object by film title
     *
     * @param title
     * @return
     */
    List<T> findByTitle(String title);
}
