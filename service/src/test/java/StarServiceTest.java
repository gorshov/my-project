import entity.Star;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.StarServiceInterface;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by Александр Горшов on 21.05.2017.
 */

@ContextConfiguration(locations = "classpath:test-service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class StarServiceTest {
    private static final Logger log = Logger.getLogger(StarServiceTest.class);

    @Autowired
    private StarServiceInterface starServiceInterface;

    @Test
    public void deleteByNameTest() throws ServiceException {
        Star star = Star.builder().firstName("Favorite").middleName("Star").lastName("Star").build();
        starServiceInterface.saveOrUpdate(star);
        starServiceInterface.deleteByName(star.getLastName());
        List<Star> starList = starServiceInterface.getAll(Star.class);
        Assert.assertEquals(starList.size(), 0);
    }

    @Test
    public void getCountForViewInPartsTest() throws ServiceException {
        Star star = Star.builder().firstName("Test").middleName("BY").lastName("Test").build();
        Star star1 = Star.builder().firstName("Test2").middleName("Test2").lastName("Test2").build();
        starServiceInterface.saveOrUpdate(star);
        starServiceInterface.saveOrUpdate(star1);
        long count = starServiceInterface.getCountForViewInParts();
        Assert.assertEquals(2, count);
    }
}
