package dao;

import entity.Review;

import java.io.Serializable;
import java.util.List;

/**
 * This interface defines special methods for working with the entity Review
 */
public interface ReviewDaoInterface<T> extends BaseDaoInterface<T> {
    /**
     * This method return all review written for film
     *
     * @param id
     * @return
     */
    List<T> getReviewForFilm(Long id);

    /**
     * This method return all review written user
     *
     * @param id
     * @return
     */
    List<T> getReviewForUser(Long id);

    /**
     * This method save review for film
     *
     * @param entity
     * @param id
     * @param name
     * @return
     */
    T saveForFilm(Review entity, long id, String name);

    /**
     * This method delete review by id
     *
     * @param id
     */
    void deleteById(Serializable id);
}
