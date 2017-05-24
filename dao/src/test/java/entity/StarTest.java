package entity;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Admin on 15.04.2017.
 */
public class StarTest {


    @Test
    public void equalsTest() throws Exception {
        Star star = new Star();
        star.setFirstName("Test");
        star.setMiddleName("Test");
        star.setLastName("Test");
        Star newStar = new Star();
        newStar.setFirstName("Test");
        newStar.setMiddleName("Test");
        newStar.setLastName("Test");
        Assert.assertEquals(star, newStar);
        Assert.assertTrue(star.equals(newStar));
    }

    @Test
    public void hashCodeTest() throws Exception {
        Star star = new Star();
        star.setFirstName("Test");
        star.setMiddleName("Test");
        star.setLastName("Test");
        Star newStar = new Star();
        newStar.setFirstName("Test");
        newStar.setMiddleName("Test");
        newStar.setLastName("Test");
        Assert.assertEquals(star.hashCode(), newStar.hashCode());
    }

}