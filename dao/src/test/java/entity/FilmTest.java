package entity;

import entity.enumiration.Genre;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 15.04.2017.
 */
public class FilmTest {
    @Test
    public void equalsTest() throws Exception {
        Film film = new Film();
        film.setTitle("TEST");
        film.setCountry("Test");
        film.setGenre(Genre.ACTION);
        Film film1 = new Film();
        film1.setTitle("TEST");
        film1.setCountry("Test");
        film1.setGenre(Genre.ACTION);
        Assert.assertEquals(film, film);
        Assert.assertTrue(film.equals(film1));

    }

    @Test
    public void hashCodeTest() throws Exception {
        Film film = new Film();
        film.setTitle("TEST");
        film.setCountry("Test");
        film.setGenre(Genre.ACTION);
        Film film1 = new Film();
        film1.setTitle("TEST");
        film1.setCountry("Test");
        film1.setGenre(Genre.ACTION);
        Assert.assertEquals(film.hashCode(), film1.hashCode());
    }

}