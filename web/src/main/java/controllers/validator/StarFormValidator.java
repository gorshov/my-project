package controllers.validator;

import entity.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.StarServiceInterface;

/**
 * Created by Александр Горшов on 11.05.2017.
 */
@Component
public class StarFormValidator implements Validator {

    @Autowired
    private StarServiceInterface serviceInterface;


    @Override
    public boolean supports(Class<?> clazz) {
        return Star.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        Star star = (Star) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.starForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middleName", "NotEmpty.starForm.middleName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.starForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "NotEmpty.starForm.dateOfBirth");
    }
}
