package entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.enumiration.Role;
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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Александр Горшов on 21.05.2017.
 */

@Entity
@Table(name = "`USER`")
@ToString(exclude = "reviewList")
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME", unique = true)
    private String lastName;

    @Column(name = "PASSWORDS", nullable = false)
    private String passwords;

    @Transient
    private String confirmPassword;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Cascade({CascadeType.REMOVE, CascadeType.SAVE_UPDATE})
    @JsonIgnore
    private List<Review> reviewList = new LinkedList<>();

    public User() {
    }

    @Builder
    public User(String firstName, String middleName, String lastName, String passwords, String confirmPassword, String email, Role role, List<Review> reviewList) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.passwords = passwords;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.role = role;
        this.reviewList = reviewList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (middleName != null ? !middleName.equals(user.middleName) : user.middleName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (passwords != null ? !passwords.equals(user.passwords) : user.passwords != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (passwords != null ? passwords.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
