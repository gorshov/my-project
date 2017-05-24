package dao.impl;


import dao.ReviewDaoInterface;
import entity.Film;
import entity.Review;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * This class contains special methods for working with the entity Review
 */
@Repository
public class ReviewDao extends BaseDao<Review> implements ReviewDaoInterface<Review> {

    private static final Logger log = Logger.getLogger(ReviewDao.class);

    @Autowired
    public ReviewDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Review> getReviewForFilm(Long id) {
        log.info("Start getReviewForFilm method " + id);
        String queryGetReviewForFilm = "select R from Review R where Film.id=:reviewId";
        Query query = getSession().createQuery(queryGetReviewForFilm);
        query.setParameter("reviewId", id);
        List<Review> reviewList = query.list();
        return reviewList;
    }

    public List<Review> getReviewForUser(Long id) {
        log.info("Start getReviewForUser method " + id);
        String queryGetReviewForUser = "select R from Review R where User.id=:reviewId";
        Query query = getSession().createQuery(queryGetReviewForUser);
        query.setParameter("reviewId", id);
        List<Review> reviewList = query.list();
        return reviewList;
    }

    @Override
    public Review saveForFilm(Review entity, long id, String name) {
        log.info("Start saveForFilm method " + " review " + entity + " film id " + id + " username " + name);
        String queryFindUser = "from User U where U.email=:name";
        String queryFindFilm = "from Film F where F.id=:id";
        Query userQuery = getSession().createQuery(queryFindUser).setParameter("name", name);
        Query filmQuery = getSession().createQuery(queryFindFilm).setParameter("id", id);
        User user = (User) userQuery.uniqueResult();
        Film film = (Film) filmQuery.uniqueResult();
        film.getReviewList().add(entity);
        user.getReviewList().add(entity);
        entity.setUser(user);
        entity.setFilm(film);
        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void deleteById(Serializable id) {
        log.info("Start deleteById method " + id);
        String deleteReview = "delete from Review R where R.id=:id";
        Query reviewQuery = getSession().createQuery(deleteReview).setParameter("id", id);
        reviewQuery.executeUpdate();
    }

}
