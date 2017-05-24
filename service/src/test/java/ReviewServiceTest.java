import entity.Film;
import entity.Review;
import entity.User;
import entity.enumiration.Genre;
import entity.enumiration.Mark;
import entity.enumiration.Role;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.FilmServiceInterface;
import service.ReviewServiceInterface;
import service.UserServiceInterface;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@ContextConfiguration(locations = "classpath*:test-service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReviewServiceTest {

    private static final Logger log = Logger.getLogger(ReviewServiceInterface.class);

    @Autowired
    private ReviewServiceInterface serviceInterface;

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private FilmServiceInterface filmServiceInterface;

    @Test
    public void saveOrUpdateTest() {
        Review review = Review.builder().text("test review").mark(Mark.Eight).build();
        serviceInterface.saveOrUpdate(review);
        Assert.assertTrue(review.getId() != 0);
        review.setMark(Mark.Five);
        Review newReview = (Review) serviceInterface.saveOrUpdate(review);
        Assert.assertEquals(review.getText(), "test review");
        Assert.assertEquals(review, newReview);
        Assert.assertEquals(review.getMark(), newReview.getMark());
    }


    @Test
    public void getAllTest() {
        Review review = Review.builder().text("test review").mark(Mark.Eight).build();
        serviceInterface.saveOrUpdate(review);
        List<Review> reviewList = serviceInterface.getAll(Review.class);
        Assert.assertTrue(reviewList.size() > 0);
        Review review1 = reviewList.get(0);
        Assert.assertEquals(review, review1);
    }

    @Test
    public void getByIdTest() {
        Review review = Review.builder().text("this all review").mark(Mark.Four).build();
        serviceInterface.saveOrUpdate(review);
        Review newReview = (Review) serviceInterface.getById(Review.class, review.getId());
        Assert.assertEquals(review, newReview);
        Assert.assertEquals(review.getId(), newReview.getId());
    }

    @Test
    public void saveForFilmTest() throws ServiceException {
        User user = User.builder().firstName("Alex").middleName("Ivanovich").lastName("Gorshov").email("strf@tut.by").passwords("12345").role(Role.ADMIN).build();
        userServiceInterface.saveOrUpdate(user);
        Film film = Film.builder().title("MyTest").country("China").genre(Genre.ADVENTURE).build();
        filmServiceInterface.saveOrUpdate(film);
        Review review = Review.builder().text("ahjsgdhg").build();
        /*Review review2 = (Review) serviceInterface.saveForFilm(review, film.getId(), user.getEmail());
        Assert.assertEquals(review, review2);
        Assert.assertEquals(review.getText(), review2.getText());
        Review review3 = (Review) serviceInterface.getById(Review.class, 1l);
        Assert.assertEquals(review, review3);*/
    }

}
