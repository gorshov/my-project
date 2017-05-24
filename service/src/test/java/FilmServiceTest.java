import entity.Film;
import entity.Review;
import entity.enumiration.Genre;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.FilmServiceInterface;
import service.exception.ServiceException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Александр Горшов on 21.05.2017.
 */

@ContextConfiguration(locations = "classpath:test-service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FilmServiceTest {

    @Autowired
    private FilmServiceInterface filmServiceInterface;

    @Test
    public void saveOrUpdateTest() {
        Film film = Film.builder().title("MyTest").country("China").genre(Genre.ADVENTURE).build();
        Film newFilm = (Film) filmServiceInterface.saveOrUpdate(film);
        Assert.assertEquals(film, newFilm);
        Assert.assertTrue(film.getId() != 0);
        newFilm = (Film) filmServiceInterface.getById(Film.class, 1l);
        Assert.assertEquals(film, newFilm);
        film.setTitle("NewTest");
        newFilm = (Film) filmServiceInterface.saveOrUpdate(film);
        Assert.assertEquals(film.getTitle(), "NewTest");
        Assert.assertEquals(film, newFilm);
    }


    @Test
    public void getAllTest() {
        Film film = Film.builder().title("Batman").country("USA").genre(Genre.ACTION).build();
        Film film2 = Film.builder().title("Sid and Nancy").country("USA").genre(Genre.DRAMA).build();
        filmServiceInterface.saveOrUpdate(film);
        List<Film> filmList = filmServiceInterface.getAll(Film.class);
        Assert.assertTrue(filmList.size() > 0);
        Film film1 = new Film();
        for (Film f : filmList) {
            if (f.getTitle().equals("Batman")) {
                film1 = f;
            }
        }
        Assert.assertEquals(film, film1);
    }

    @Test
    public void getByIdTest() {
        Film film = Film.builder().title("Titanic").country("Usa").genre(Genre.DRAMA).build();
        filmServiceInterface.saveOrUpdate(film);
        Film newFilm = (Film) filmServiceInterface.getById(Film.class, film.getId());
        Assert.assertEquals(film, newFilm);
    }

    @Test
    public void deleteById() throws ServiceException {
        Film film = Film.builder().title("Fight Club").country("USA").genre(Genre.ACTION).build();
        filmServiceInterface.saveOrUpdate(film);
        Film newFilm = (Film) filmServiceInterface.getById(Film.class, film.getId());
        Assert.assertEquals(film, newFilm);
        filmServiceInterface.deleteById(film.getId());
        Film nextNewFilm = (Film) filmServiceInterface.getById(Film.class, 1L);
    }

    @Test
    public void getCountForViewInPartsTest() throws ServiceException {
        Film film = Film.builder().title("Test").country("BY").genre(Genre.ACTION).build();
        Film film1 = Film.builder().title("Test2").country("Ru").genre(Genre.WAR).build();
        filmServiceInterface.saveOrUpdate(film);
        filmServiceInterface.saveOrUpdate(film1);
        long count = filmServiceInterface.getCountForViewInParts();
        Assert.assertEquals(2, count);
    }

    @Test
    public void getFilmsByYearTest() throws ServiceException {

    }

    @Test
    public void findByTitleTest() throws ServiceException {
        Film film = Film.builder().title("Test").country("BY").genre(Genre.ACTION).releaseDate(Date.valueOf(
                LocalDate.of(2017, 05, 22))).build();
        filmServiceInterface.saveOrUpdate(film);
        List<Film> filmList = filmServiceInterface.findByTitle("Test");
        Film newFilm = new Film();
        for (Film f : filmList) {
            newFilm = f;
        }
        Assert.assertEquals(film, newFilm);
    }
}
