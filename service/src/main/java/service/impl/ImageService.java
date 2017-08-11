package service.impl;

import dao.ImageDaoInterface;
import entity.Film;
import entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.FilmServiceInterface;
import service.ImageServiceInterface;

/**
 * Created by Александр Горшов on 04.06.2017  12:43.
 */
@Service
@Transactional
public class ImageService extends BaseService<Image> implements ImageServiceInterface<Image> {

    @Autowired
    @Qualifier("imageDao")
    private ImageDaoInterface imageDaoInterface;
}
