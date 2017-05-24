package controllers.validator;

import entity.UploadedFile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Александр Горшов on 20.05.2017.
 */
@Component
public class UploadedFileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UploadedFile.class.equals(clazz);
    }

    @Override
    public void validate(Object uploadedFile, Errors errors) {
        UploadedFile file = (UploadedFile) uploadedFile;
        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "uploadForm.selectFile", "Please select a file!");
        }
    }
}
