package entity;

import entity.enumiration.Role;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 15.04.2017.
 */
public class UserTest {


    @Test
    public void hashCodeTest() throws Exception {
        User user = new User();
        user.setLastName("Tom");
        user.setEmail("tut@net.by");
        user.setPasswords("12345");
        user.setRole(Role.USER);
        User user1 = new User();
        user1.setLastName("Tom");
        user1.setEmail("tut@net.by");
        user1.setPasswords("12345");
        user1.setRole(Role.USER);
        Assert.assertEquals(user.hashCode(), user1.hashCode());
        Assert.assertTrue(user.equals(user1));
    }

    @Test
    public void equals() throws Exception {
        User user = new User();
        user.setLastName("Tom");
        user.setEmail("tut@net.by");
        user.setPasswords("12345");
        user.setRole(Role.USER);
        User user1 = new User();
        user1.setLastName("Tom");
        user1.setEmail("tut@net.by");
        user1.setPasswords("12345");
        user1.setRole(Role.USER);
        Assert.assertEquals(user, user1);
    }

}