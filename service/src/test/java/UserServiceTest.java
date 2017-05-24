import entity.User;
import entity.enumiration.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.UserServiceInterface;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@ContextConfiguration(locations = "classpath:test-service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserServiceInterface serviceInterface;

    @Test
    public void getAllTest() {
        User user = User.builder().firstName("Alex").middleName("Ivanovich").lastName("Gorshov").email("strf@tut.by").passwords("12345").role(Role.ADMIN).build();
        serviceInterface.saveOrUpdate(user);
        List<User> userList = serviceInterface.getAll(User.class);
        Assert.assertNotNull(userList);
        Assert.assertTrue(userList.size() > 0);
    }

    @Test
    public void registrationTest() throws ServiceException {
        User user = User.builder().firstName("Alex").middleName("Ivanovich").lastName("NotGorshov").email("mymail@tut.by").passwords("12345").build();
        serviceInterface.registration(user);
        User user1 = (User) serviceInterface.getByLastName("NotGorshov");
        Assert.assertEquals(user.getRole(), user1.getRole());
        Assert.assertEquals(user, user1);
    }

    @Test
    public void getByLastNameTest() throws ServiceException {
        User user = User.builder().firstName("Bruce").lastName("Dickinson").email("hismail@tut.by").passwords("12345").role(Role.ADMIN).build();
        serviceInterface.saveOrUpdate(user);
        User newUser = (User) serviceInterface.getByLastName("Dickinson");
        Assert.assertEquals(user.getLastName(), newUser.getLastName());
        Assert.assertEquals(user, newUser);
    }


    @Test
    public void getUserByEmailTest() throws ServiceException {
        User user = User.builder().firstName("Test").middleName("Test").lastName("Test").email("strr@tut.by")
                .passwords("12345").role(Role.ADMIN).build();
        serviceInterface.saveOrUpdate(user);
        User user1 = serviceInterface.getUserByEmail("strr@tut.by");
        Assert.assertEquals(user, user1);
    }

    @Test
    public void findUserTest() throws ServiceException {
        User user = User.builder().firstName("Test").middleName("Test").lastName("NewTes").email("strr@google.com")
                .passwords("12345").role(Role.USER).build();
        serviceInterface.saveOrUpdate(user);
        User newUser = (User) serviceInterface.findUser("strr@google.com", "12345");
        Assert.assertEquals(user.getEmail(), newUser.getEmail());
        Assert.assertEquals(user, newUser);
    }

}
