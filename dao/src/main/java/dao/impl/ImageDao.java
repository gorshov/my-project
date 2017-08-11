package dao.impl;

import dao.ImageDaoInterface;
import entity.Image;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Александр Горшов on 01.06.2017  20:57.
 */
@Repository
public class ImageDao extends BaseDao<Image> implements ImageDaoInterface<Image> {

    @Autowired
    public ImageDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
