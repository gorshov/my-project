package entity;

import entity.enumiration.Mark;
import entity.enumiration.Role;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 15.04.2017.
 */
public class ReviewTest {
    @Test
    public void equalsTest() throws Exception {
        User user = new User();
        user.setLastName("Tom");
        user.setEmail("tut@net.by");
        user.setPasswords("12345");
        user.setRole(Role.USER);
        Review review = new Review();
        review.setUser(user);
        review.setMark(Mark.Ten);
        review.setText("TEST");
        Review review1 = new Review();
        review1.setUser(user);
        review1.setMark(Mark.Ten);
        review1.setText("TEST");
        Assert.assertEquals(review, review1);
        Assert.assertTrue(review.equals(review1));

    }

    @Test
    public void hashCodeTest() throws Exception {
        User user = new User();
        user.setLastName("Tom");
        user.setEmail("tut@net.by");
        user.setPasswords("12345");
        user.setRole(Role.USER);
        Review review = new Review();
        review.setUser(user);
        review.setMark(Mark.Ten);
        review.setText("TEST");
        Review review1 = new Review();
        review1.setUser(user);
        review1.setMark(Mark.Ten);
        review1.setText("TEST");
        Assert.assertEquals(review.hashCode(), review1.hashCode());
    }

}