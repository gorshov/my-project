package controllers;

import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.UserServiceInterface;

/**
 * Created by Александр Горшов on 24.05.2017.
 */
@RestController
public class RestUserController {

    private static final Logger log = Logger.getLogger(RestUserController.class);

    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping(value = "/ajax/find/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findStreet(@PathVariable("id") long id) {
        return (User) userServiceInterface.getById(User.class, id);
    }
}