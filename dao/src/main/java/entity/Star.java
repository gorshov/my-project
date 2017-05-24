package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр Горшов on 21.05.2017.
 */

@Getter
@Setter
@Entity
@Table(name = "STAR")
@ToString(exclude = "filmSet")
public class Star implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STAR_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @ManyToMany(mappedBy = "starSet")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Film> filmSet = new HashSet<>();

    public Star() {
    }

    @Builder
    public Star(String firstName, String middleName, String lastName, Date dateOfBirth, Set<Film> filmSet) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.filmSet = filmSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Star)) return false;

        Star star = (Star) o;

        if (id != null ? !id.equals(star.id) : star.id != null) return false;
        if (firstName != null ? !firstName.equals(star.firstName) : star.firstName != null) return false;
        if (middleName != null ? !middleName.equals(star.middleName) : star.middleName != null) return false;
        if (lastName != null ? !lastName.equals(star.lastName) : star.lastName != null) return false;
        return dateOfBirth != null ? dateOfBirth.equals(star.dateOfBirth) : star.dateOfBirth == null;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }
}
