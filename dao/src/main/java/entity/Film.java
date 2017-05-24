package entity;

import entity.enumiration.Genre;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

/**
 * Created by Александр Горшов on 21.05.2017.
 */

@Getter
@Setter
@ToString(exclude = {"reviewList", "starSet"})
@Entity
@Table(name = "FILM")
/*
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
*/
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILM_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "GENRE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @Column(name = "COUNTRY")
    private String country;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Review> reviewList = new LinkedList<>();

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "FILM_STAR", joinColumns = {@JoinColumn(name = "FILM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STAR_ID")})
    private Set<Star> starSet = new HashSet<>();

    public Film() {
    }

    @Builder
    public Film(String title, Genre genre, Date releaseDate, String country, List<Review> reviewList, Set<Star> starSet) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.country = country;
        this.reviewList = reviewList;
        this.starSet = starSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;

        Film film = (Film) o;

        if (id != null ? !id.equals(film.id) : film.id != null) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (genre != film.genre) return false;
        if (releaseDate != null ? !releaseDate.equals(film.releaseDate) : film.releaseDate != null) return false;
        return country != null ? country.equals(film.country) : film.country == null;

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
