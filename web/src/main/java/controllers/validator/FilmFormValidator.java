package controllers.validator;

import entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.FilmServiceInterface;

/**
 * Created by Александр Горшов on 20.05.2017.
 */
@Component
public class FilmFormValidator implements Validator {

    @Autowired
    private FilmServiceInterface serviceInterface;

    @Override
    public boolean supports(Class<?> clazz) {
        return Film.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Film film = (Film) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.filmForm.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "releaseDate", "NotEmpty.filmForm.releaseDate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.filmForm.country");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "NotEmpty.filmForm.genre");
    }

}
