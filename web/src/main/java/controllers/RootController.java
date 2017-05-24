package controllers;

import controllers.validator.UserFormValidator;
import entity.User;
import entity.enumiration.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import service.exception.ServiceException;
import utils.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Controller
public class RootController {
    private static final Logger log = Logger.getLogger(RootController.class);

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private UserFormValidator userFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userFormValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        log.debug("index()");
        return "redirect:/films";
    }

    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap modelMap) {
        log.debug("registration()");
        User user = new User();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("registrationForm", user);
        modelMap.addAttribute("roleList", getRole());
        return "users/registration-form";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("registrationForm") @Validated User user,
                               BindingResult result, Model model) {
        log.debug("registration() post: ");
        User newUser = null;
        try {
            newUser = (User) userService.findUser(user.getEmail(), user.getPasswords());
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", "error.messages.add.review");
            return "film/list";
        }
        model.addAttribute("css", "success");
        if (result.hasErrors()) {
            return "users/registration-form";
        } else if (user.equals(newUser)) {
            model.addAttribute("msg", "The account is already in use!");
            return "films/list";
        } else if (user.getId() == null) {
            model.addAttribute("msg", "User added successfully!");
        }
        try {
            userService.registration(user);
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("msg", "error.messages.add.review");
            return "film/list";
        }
        return "films/list";
    }

    @RequestMapping(value = {"/access_denied"})
    public String deniedAccessPage(@RequestParam(name = "error") String error, ModelMap modelMap) {
        switch (error) {
            case "authError":
                modelMap.addAttribute("authError", "authenticationError");
            case "accessError":
                modelMap.addAttribute("accessError", "accessError");
        }
        return "films/list";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/films";
    }

    private List<Role> getRole() {
        return new ArrayList<>(EnumSet.allOf(Role.class));
    }


}
