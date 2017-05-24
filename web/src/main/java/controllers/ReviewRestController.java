package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Review;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import service.ReviewServiceInterface;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Александр Горшов on 24.05.2017.
 */
@RestController
public class ReviewRestController {
    private static final Logger log = Logger.getLogger(ReviewRestController.class);

    @Autowired
    private ReviewServiceInterface reviewServiceInterface;

    @PostMapping(value = "/review/add/rest/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> addReviewForFilm(@PathVariable("id") long id, Review review, HttpServletResponse response) throws ServiceException {
        ObjectMapper mapper = new ObjectMapper();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        review.setId(null);
        Review newReview = (Review) reviewServiceInterface.saveForFilm(review, id, username);
        response.setHeader("/films", "/" + id);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @GetMapping(value = "/reviews/")
    public ResponseEntity<List<Review>> listAllReview() {
        List<Review> reviews = reviewServiceInterface.getAll(Review.class);
        if (reviews.isEmpty()) {
            return new ResponseEntity<List<Review>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }

}
