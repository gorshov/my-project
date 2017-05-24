package service.impl;

import dao.ReviewDaoInterface;
import dao.UserDaoInterface;
import entity.Film;
import entity.Review;
import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.FilmServiceInterface;
import service.ReviewServiceInterface;
import service.UserServiceInterface;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Service
@Transactional
public class ReviewService extends BaseService<Review> implements ReviewServiceInterface<Review> {
    private static Logger log = Logger.getLogger(ReviewService.class);

    @Autowired
    @Qualifier("reviewDao")
    private ReviewDaoInterface daoInterface;

    @Override
    public Review saveForFilm(Review entity, long id, String name) throws ServiceException {
        log.info("Start saveForFilm method :" + entity + " film id " + id + " username " + name);
        return (Review) daoInterface.saveForFilm(entity, id, name);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        log.info("Start deleteById method :" + id);
        daoInterface.deleteById(id);
    }

}
