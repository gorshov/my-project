package controllers;

import entity.Review;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ReviewServiceInterface;
import service.exception.ServiceException;
import utils.MessageManager;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Controller
public class ReviewController {

    private static final Logger log = Logger.getLogger(ReviewController.class);

    @Autowired
    private ReviewServiceInterface reviewServiceInterface;

    @RequestMapping(value = "/review/add/{id}", method = RequestMethod.POST)
    public String addReviewForFilm(Review review, @PathVariable("id") long id, ModelMap model) {
        log.debug("addReviewForFilm()");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        review.setId(null);
        try {
            reviewServiceInterface.saveForFilm(review, id, username);
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", MessageManager.getProperty("error.messages.add.review"));
            return "films/list";
        }
        return "redirect:/films/" + id;
    }

    @RequestMapping(value = "/review/{id}/{filmId}/delete", method = RequestMethod.POST)
    public String deleteReview(@PathVariable("id") long id, @PathVariable("filmId") long filmId, ModelMap model) {
        try {
            reviewServiceInterface.deleteById(id);
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", MessageManager.getProperty("error.messages"));
            return "films/list";
        }
        return "redirect:/films/" + filmId;
    }

}
