package service.impl;

import dao.BaseDaoInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BaseServiceInterface;

import javax.swing.border.TitledBorder;
import java.io.Serializable;
import java.util.List;

/**
 * This common base class
 */
@Service
@Transactional
public class BaseService<T> implements BaseServiceInterface<T> {
    private static Logger log = Logger.getLogger(BaseService.class);

    @Autowired
    @Qualifier("baseDao")
    protected BaseDaoInterface<T> daoInterface;

    public BaseService() {
    }

    @Autowired
    public BaseService(BaseDaoInterface<T> daoInterface) {
        this.daoInterface = daoInterface;
    }

    @Override
    public T saveOrUpdate(T entity) {
        log.info("Start method saveOrUpdate" + entity);
        daoInterface.saveOrUpdate(entity);
        return (T) entity;
    }

    @Override
    public T getById(Class clazz, Serializable id) {
        log.info("Start method getById " + id);
        return (T) daoInterface.getById(clazz, id);
    }

    @Override
    public List<T> getAll(Class clazz) {
        log.info("Start method getAll " + clazz);
        return daoInterface.getAll(clazz);
    }

}
