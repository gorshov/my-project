package controllers;

import entity.Film;
import entity.Star;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.StarServiceInterface;
import controllers.validator.StarFormValidator;
import service.exception.ServiceException;
import utils.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Controller
public class StarController {
    private static final Logger log = Logger.getLogger(StarController.class);

    @Autowired
    private StarServiceInterface serviceInterface;

    @Autowired
    private StarFormValidator formValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(formValidator);
    }

    @RequestMapping(value = "/stars", method = RequestMethod.POST)
    public String saveOrUpdateStar(@ModelAttribute("starForm") @Validated Star star, BindingResult result,
                                   HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        log.debug("saveOrUpdateUser() : ");
        if (result.hasErrors()) {
            return "stars/star-form";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (star.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "Star added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Star updated successfully!");
            }
            serviceInterface.saveOrUpdate(star);
            return "redirect:/star/" + 1 + "/" + 10;
        }
    }

    @RequestMapping(value = "/stars/addFilm", method = RequestMethod.POST)
    public String saveStarInFilm(@ModelAttribute("starForm") @Validated Star star, BindingResult result,
                                 HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        log.debug("saveOrUpdateUser() : ");
        Long idFilm = Long.parseLong(request.getParameter("filmId"));
        Film film = (Film) serviceInterface.getById(Film.class, idFilm);
        if (result.hasErrors()) {
            return "stars/star-form";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Star added successfully!");
        }
        film.getStarSet().add(star);
        serviceInterface.saveOrUpdate(star);
        return "redirect:/films/" + film.getId();

    }

    @RequestMapping(value = "/stars/add", method = RequestMethod.GET)
    public String showAddStarForm(Model model) {
        log.debug("showAddStarForm()");
        Star star = new Star();
        model.addAttribute("star", star);
        model.addAttribute("starForm", star);
        return "stars/star-form";
    }

    @RequestMapping(value = "/stars/add/film{id}", method = RequestMethod.GET)
    public String addStarInFilm(@PathVariable("id") long filmId, Model model) {
        log.debug("addStarInFilm()");
        Star star = new Star();
        model.addAttribute("filmId", filmId);
        model.addAttribute("star", star);
        model.addAttribute("starForm", star);
        return "stars/star-in-film-form";
    }

    @RequestMapping(value = "/stars/{id}/update", method = RequestMethod.GET)
    public String showUpdateStarForm(@PathVariable("id") long id, Model model) {
        log.debug("showUpdateStarForm() : ");
        Star star = (Star) serviceInterface.getById(Star.class, id);
        model.addAttribute("star", star);
        model.addAttribute("starForm", star);
        return "stars/star-form";

    }

    @RequestMapping(value = "/stars/{lastName}/delete", method = RequestMethod.POST)
    public String deleteStar(@PathVariable("lastName") String lastName, final RedirectAttributes redirectAttributes, ModelMap model) {
        log.debug("deleteStar() : " + lastName);
        try {
            serviceInterface.deleteByName(lastName);
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", "error.messages");
            return "stars/list";
        }
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Star is deleted!");
        return "redirect:/star/" + 1 + "/" + 10;

    }

    @RequestMapping(value = "/stars/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") long id, Model model) {
        log.debug("showUser() id: ");
        Star star = (Star) serviceInterface.getById(Star.class, id);
        if (star == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Star not found");
        }
        model.addAttribute("star", star);
        return "stars/show";
    }

    @RequestMapping(value = "/star/{pageNumber}/{recordPerPage}", method = RequestMethod.GET)
    public String showFilmPagination(@PathVariable(value = "pageNumber") int pageNumber,
                                     @PathVariable(value = "recordPerPage") int recordPerPage, ModelMap model) {
        try {
            Set<Star> starSet = serviceInterface.getStarsForViewInParts(pageNumber, recordPerPage);
            long count = serviceInterface.getCountForViewInParts();
            long numberOfPages = count / recordPerPage;
            model.addAttribute("starSet", starSet);
            model.addAttribute("count", count);
            model.addAttribute("numberOfPages", numberOfPages + 1);
            model.addAttribute("page", pageNumber);
            model.addAttribute("recordPerPage", recordPerPage);
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", MessageManager.getProperty("error.messages"));
            return "films/list";
        }
        return "stars/list";
    }
}
