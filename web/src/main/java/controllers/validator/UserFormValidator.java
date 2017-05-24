package controllers.validator;

import controllers.FilmController;
import entity.User;
import entity.enumiration.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.UserServiceInterface;
import service.exception.ServiceException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Александр Горшов on 20.05.2017.
 */
@Component
public class UserFormValidator implements Validator {

    @Autowired
    @Qualifier("emailValidator")
    private EmailValidator emailValidator;

    @Autowired
    @Qualifier("userService")
    private UserServiceInterface serviceInterface;


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        List<User> userList = serviceInterface.getAll(User.class);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middleName", "NotEmpty.userForm.middleName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwords", "NotEmpty.userForm.passwords");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.userForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "NotEmpty.userForm.role");

        if (!emailValidator.valid(user.getEmail())) {
            errors.rejectValue("email", "Pattern.userForm.email");
        }

        if (!user.getPasswords().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
        }

        if (!user.getLastName().isEmpty()) {
            for (User u : userList) {
                if (user.getLastName().equals(u.getLastName())) {
                    errors.rejectValue("lastName", "Repeat.userForm.lastName");
                }
            }
        }
    }
}
