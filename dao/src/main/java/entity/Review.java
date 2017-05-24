package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.enumiration.Mark;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Александр Горшов on 21.05.2017.
 */

@Entity
@Table(name = "REVIEW")
@ToString
@Getter
@Setter
/*
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
*/
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FILM_ID")
    @Cascade(CascadeType.ALL)
    @JsonIgnore
    private Film film;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "MARK")
    @Enumerated(EnumType.ORDINAL)
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Review() {
    }

    @Builder
    public Review(Film film, String text, Mark mark, User user) {
        this.film = film;
        this.text = text;
        this.mark = mark;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;

        Review review = (Review) o;

        if (mark != review.mark) return false;
        if (id != null ? !id.equals(review.id) : review.id != null) return false;
        if (film != null ? !film.equals(review.film) : review.film != null) return false;
        if (text != null ? !text.equals(review.text) : review.text != null) return false;
        return user != null ? user.equals(review.user) : review.user == null;

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
