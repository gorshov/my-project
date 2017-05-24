package entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
public class UploadedFile {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
