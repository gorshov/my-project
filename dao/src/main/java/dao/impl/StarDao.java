package dao.impl;


import dao.StarDaoInterface;
import entity.Star;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * This class contains special methods for working with the entity Star
 */
@Repository
public class StarDao extends BaseDao<Star> implements StarDaoInterface<Star> {
    private static final Logger logger = Logger.getLogger(StarDao.class);

    @Autowired
    public StarDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Star> getFilmByActor(String lastName) {
        logger.info("getFilmInActor star" + lastName);
        Criteria criteria = getSession().createCriteria(Star.class);
        criteria.add(Restrictions.eq("lastName", lastName)).setFetchMode("filmSet", FetchMode.JOIN);
        return criteria.list();

    }

    @Override
    public void deleteByName(String name) {
        logger.info("deleteByName star" + name);
        String queryDeleteByNAme = "DELETE FROM Star s WHERE s.lastName=:lastName";
        Query query = getSession().createQuery(queryDeleteByNAme).setParameter("lastName", name);
        query.executeUpdate();
    }

    @Override
    public List<Star> getStarsForViewInParts(int pageNumber, int recordPerPage) {
        logger.info("Start getStarsForViewInParts method " + "number page " + pageNumber + " and " + recordPerPage);
        String findAllStar = "from Star";
        int firstResult = (pageNumber - 1) * recordPerPage;
        Query query = getSession().createQuery(findAllStar).setFirstResult(firstResult).setMaxResults(recordPerPage);
        return query.list();
    }

    @Override
    public long getCountForViewInParts() {
        logger.info("Start getCountForViewInParts method ");
        String findCountStars = "select count(S) from Star S";
        Query query = getSession().createQuery(findCountStars);
        return (long) query.uniqueResult();
    }

    @Override
    public void deleteById(Serializable id) {

    }

}
