package dao.impl;

import dao.FilmDaoInterface;
import entity.Film;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This class contains special methods for working with the entity Film
 */
@Repository
public class FilmDao extends BaseDao<Film> implements FilmDaoInterface<Film> {
    private static final Logger log = Logger.getLogger(FilmDao.class);

    @Autowired
    public FilmDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Film> getFilmsByYear(String year) {
        log.info("Start getFilmsByYear method " + year);
        //TODO create method search by year
        List<Film> filmList = new ArrayList<>();
        return filmList;
    }

    @Override
    public void deleteById(Serializable id) {
        log.info("Start deleteById method " + id);
        String deleteFilmById = "delete from Film F where F.id=:id";
        String deleteReviewByFilm = "delete from Review R where film=:film";
        Query queryDeleteById = getSession().createQuery(deleteFilmById).setParameter("id", id);
        Film film = (Film) getSession().get(Film.class, id);
        Query queryDeleteReview = getSession().createQuery(deleteReviewByFilm).setParameter("film", film);
        queryDeleteReview.executeUpdate();
        queryDeleteById.executeUpdate();

    }

    @Override
    public List<Film> getFilmsForViewInParts(int pageNumber, int recordPerPage) {
        log.info("Start getFilmsForViewInParts method " + "number page " + pageNumber + " and " + recordPerPage);
        String findAllFilm = "from Film";
        int firstResult = (pageNumber - 1) * recordPerPage;
        Query query = getSession().createQuery(findAllFilm).setFirstResult(firstResult).setMaxResults(recordPerPage);
        return query.list();
    }

    @Override
    public long getCountForViewInParts() {
        log.info("Start getCountForViewInParts method ");
        String findCountAllFilm = "select count(F) from Film F order by F.title asc";
        Query query = getSession().createQuery(findCountAllFilm);
        return (long) query.uniqueResult();
    }

    @Override
    public List<Film> findByTitle(String title) {
        String findQuery = "select F from Film F where F.title=:title";
        Query query = getSession().createQuery(findQuery).setParameter("title", title);
        List<Film> filmList = query.list();
        return filmList;
    }

}
