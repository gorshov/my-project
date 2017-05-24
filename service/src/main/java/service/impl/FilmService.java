package service.impl;

import dao.FilmDaoInterface;
import entity.Film;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.FilmServiceInterface;
import service.exception.ServiceException;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Service
@Transactional
public class FilmService extends BaseService<Film> implements FilmServiceInterface<Film> {
    private static Logger log = Logger.getLogger(FilmService.class);

    @Autowired
    @Qualifier("filmDao")
    private FilmDaoInterface daoInterface;

    @Override
    public void deleteById(Long id) throws ServiceException {
        log.info("Start method deleteById " + id);
        daoInterface.deleteById(id);
    }

    @Override
    public Set<Film> getFilmsForViewInParts(int pageNumber, int recordPerPage) throws ServiceException {
        log.info("getMoviesForSpecialSearch " + pageNumber + " " + recordPerPage);
        return new HashSet<>(daoInterface.getFilmsForViewInParts(pageNumber, recordPerPage));
    }

    @Override
    @Transactional(readOnly = true)
    public long getCountForViewInParts() throws ServiceException {
        log.info("Start method deleteById ");
        return daoInterface.getCountForViewInParts();
    }

    @Override
    public List<Film> getFilmsByYear(String yaer) throws ServiceException {
        log.info("Start method getFilmsByYear " + yaer);
        List<Film> filmList = daoInterface.getFilmsByYear(yaer);
        return filmList;
    }

    @Override
    public List<Film> findByTitle(String title) throws ServiceException {
        log.info("Start method findByTitle " + title);
        List<Film> filmList = daoInterface.findByTitle(title);
        return filmList;
    }

}
