package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Александр Горшов on 01.06.2017  18:31.
 */
@Entity
@Table(name = "IMAGE")
@NoArgsConstructor
@ToString(exclude = "film")
@AllArgsConstructor
@Getter
@Setter
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID", nullable = true)
    private Long id;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Transient
    private MultipartFile file;

    @Column(name = "STATUS")
    private String status;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "image")
    private Film film;

    public Image(String imagePath, String status) {
        this.imagePath = imagePath;
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (id != null ? !id.equals(image.id) : image.id != null) return false;
        if (imagePath != null ? !imagePath.equals(image.imagePath) : image.imagePath != null) return false;
        return status != null ? status.equals(image.status) : image.status == null;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
