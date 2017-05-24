package controllers;

import entity.User;
import entity.enumiration.Role;
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
import service.UserServiceInterface;
import controllers.validator.UserFormValidator;
import service.exception.ServiceException;
import utils.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Controller
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private UserFormValidator userFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userFormValidator);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        log.debug("showAllUsers()");
        List<User> userList = userService.getAll(User.class);
        long count = 0;
        try {
            count = userService.getCountUsers();
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", "error.messages");
        }
        model.addAttribute("users", userList);
        model.addAttribute("count", count);
        return "users/list";

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user,
                                   BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        log.debug("saveOrUpdateUser() : ");
        if (result.hasErrors()) {
            return "users/user-form";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (user.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "User added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
            }
            userService.saveOrUpdate(user);
            return "redirect:/users/" + user.getId();
        }
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String showAddUserForm(ModelMap model) {
        log.debug("showAddUserForm()");
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userForm", user);
        model.addAttribute("roleList", getRole());
        return "users/user-form";
    }

    @RequestMapping(value = "/user/search/", method = RequestMethod.POST)
    public String searchFilmsByYear(@RequestParam String name, ModelMap model, final RedirectAttributes redirectAttributes) {
        try {
            List<User> userList = new ArrayList<>();
            User user = (User) userService.getByLastName(name);
            redirectAttributes.addFlashAttribute("css", "success");
            if (user == null) {
                redirectAttributes.addFlashAttribute("msg", "not found");
                return "redirect:/users";
            } else {
                userList.add(user);
                model.addAttribute("users", userList);
            }
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("msg", MessageManager.getProperty("error.messages"));
            return "users/list";
        }
        return "users/list";
    }

    @RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") long id, Model model) {
        log.debug("showUpdateUserForm() : ");
        User user = (User) userService.getById(User.class, id);
        model.addAttribute("userForm", user);
        return "users/user-form";
    }

    @RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") long id, final RedirectAttributes redirectAttributes, ModelMap model) {
        log.debug("deleteUser() : {}");
        try {
            userService.deleteById(id);
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", "error.messages");
            return "users/list";
        }
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is deleted!");
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") long id, Model model) {
        log.debug("showUser() id: ");
        User user = (User) userService.getById(User.class, id);
        if (user == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }
        model.addAttribute("user", user);
        return "users/show";
    }

    @RequestMapping(value = "/users/upload", method = RequestMethod.POST)
    public String uploadImage() {
        return null;
    }

    private List<Role> getRole() {
        return new ArrayList<>(EnumSet.allOf(Role.class));
    }
}
