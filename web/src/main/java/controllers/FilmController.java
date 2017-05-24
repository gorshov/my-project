package controllers;

import entity.Film;
import entity.enumiration.Genre;
import entity.enumiration.Mark;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.FilmServiceInterface;
import controllers.validator.FilmFormValidator;
import service.exception.ServiceException;
import utils.MessageManager;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Controller
public class FilmController {
    private static final Logger log = Logger.getLogger(FilmController.class);

    @Autowired
    private FilmServiceInterface filmServiceInterface;

    @Autowired
    private FilmFormValidator filmFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(filmFormValidator);
    }

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public String showAllFilms(Model model) {
        log.debug("showAllFilms()");
        model.addAttribute("filmList", filmServiceInterface.getAll(Film.class));
        return "films/list";
    }

    @RequestMapping(value = "/films", method = RequestMethod.POST)
    public String saveOrUpdateFilm(@ModelAttribute("filmForm") @Validated Film film, BindingResult result,
                                   Model model, final RedirectAttributes redirectAttributes) {
        log.debug("saveOrUpdateFilm() : ");
        if (result.hasErrors()) {
            return "films/film-form";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (film.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "Film added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Film updated successfully!");
            }
            filmServiceInterface.saveOrUpdate(film);
            return "redirect:/films";
        }
    }

    @RequestMapping(value = "/films/add", method = RequestMethod.GET)
    public String showAddFilmForm(Model model) {
        log.debug("showAddUserForm()");
        Film film = new Film();
        model.addAttribute("film", film);
        model.addAttribute("filmForm", film);
        model.addAttribute("genres", getGenres());
        return "films/film-form";
    }

    @RequestMapping(value = "/films/search/", method = RequestMethod.POST)
    public String searchFilmsByYear(@RequestParam String title, ModelMap model, final RedirectAttributes redirectAttributes) {
        try {
            List<Film> filmList = filmServiceInterface.findByTitle(title);
            redirectAttributes.addFlashAttribute("css", "success");
            if (filmList.size() == 0) {
                redirectAttributes.addFlashAttribute("msg", "not found");
                return "redirect:/films";
            } else {
                model.addAttribute("filmList", filmList);
            }
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", MessageManager.getProperty("error.messages"));
            return "films/list";
        }
        return "films/list";
    }

    @RequestMapping(value = "/films/{id}/update", method = RequestMethod.GET)
    public String showUpdateFilmForm(@PathVariable("id") long id, Model model) {
        log.debug("showUpdateStarForm() : ");
        Film film = (Film) filmServiceInterface.getById(Film.class, id);
        model.addAttribute("genres", getGenres());
        model.addAttribute("filmForm", film);
        return "films/film-form";
    }

    @RequestMapping(value = "/films/{id}/delete", method = RequestMethod.POST)
    public String deleteFilm(@PathVariable("id") long id, final RedirectAttributes redirectAttributes, ModelMap model) {
        log.debug("deleteFilm() : ");
        try {
            filmServiceInterface.deleteById(id);
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", MessageManager.getProperty("error.messages"));
            return "films/list";
        }
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Film is deleted!");
        return "redirect:/films";
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.GET)
    public String showFilm(@PathVariable("id") long id, Model model, final RedirectAttributes redirectAttributes) {
        log.debug("showFilm() id: ");
        Film film = (Film) filmServiceInterface.getById(Film.class, id);
        if (film == null) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Film not found");
            return "redirect:/films";
        }
        model.addAttribute("marks", getMarks());
        model.addAttribute("film", film);
        return "films/show";
    }

    @RequestMapping(value = "/film/pagination/{pageNumber}/{recordPerPage}", method = RequestMethod.GET)
    public String showFilmPagination(@PathVariable(value = "pageNumber") int pageNumber,
                                     @PathVariable(value = "recordPerPage") int recordPerPage, ModelMap model) {
        long count;
        Set<Film> films;
        try {
            films = filmServiceInterface.getFilmsForViewInParts(pageNumber, recordPerPage);
            count = filmServiceInterface.getCountForViewInParts();
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", MessageManager.getProperty("error.messages"));
            return "films/list";
        }
        long numberOfPages = count / recordPerPage;
        model.addAttribute("films", films);
        model.addAttribute("count", count);
        model.addAttribute("numberOfPages", numberOfPages + 1);
        model.addAttribute("page", pageNumber);
        model.addAttribute("recordPerPage", recordPerPage);
        return "films/list-by-page";
    }

    private List<Genre> getGenres() {
        return new ArrayList<>(EnumSet.allOf(Genre.class));
    }

    private List<Mark> getMarks() {
        return new ArrayList<>(EnumSet.allOf(Mark.class));
    }
}
