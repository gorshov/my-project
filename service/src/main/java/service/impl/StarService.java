package service.impl;

import dao.StarDaoInterface;
import entity.Star;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.StarServiceInterface;
import service.exception.ServiceException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Service
@Transactional
public class StarService extends BaseService<Star> implements StarServiceInterface<Star> {
    private static Logger log = Logger.getLogger(StarService.class);

    @Autowired
    @Qualifier("starDao")
    private StarDaoInterface daoInterface;

    @Override
    public void deleteByName(String name) throws ServiceException {
        log.info("Start deleteByName method :");
        daoInterface.deleteByName(name);
    }

    @Override
    public Set<Star> getStarsForViewInParts(int pageNumber, int recordPerPage) throws ServiceException {
        log.info("Start getStarsForViewInParts method :" + pageNumber + " " + recordPerPage);
        return new HashSet<Star>(daoInterface.getStarsForViewInParts(pageNumber, recordPerPage));
    }

    @Override
    @Transactional(readOnly = true)
    public long getCountForViewInParts() throws ServiceException {
        log.info("Start getCountForViewInParts");
        return daoInterface.getCountForViewInParts();
    }
}
