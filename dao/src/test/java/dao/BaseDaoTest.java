package dao;

import entity.Film;
import entity.enumiration.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 02.05.2017.
 */
/*@ContextConfiguration(locations = "classpath:bean-db-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BaseDaoTest {

    @Autowired
    private FilmDaoInterface filmDaoInterface;

   *//* @Test
    public void saveOrUpdateTest() {
        Film film = Film.builder().title("Test").genre(Genre.ACTION).country("USA").build();
        filmDaoInterface.saveOrUpdate(film);
        Film newFilm = (Film) filmDaoInterface.getById(Film.class, 1l);
        Assert.assertEquals(film, newFilm);
    }*//*

}*/
