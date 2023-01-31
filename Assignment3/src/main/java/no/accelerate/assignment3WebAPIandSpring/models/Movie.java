package no.accelerate.assignment3WebAPIandSpring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String picture;
    private String trailer;

    //One-to-many relationship between franchises and movie

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
    /**
     * Many-to-many relationship between character and movie entities
     * The relationship is called "character_movie"
     */
    @ManyToMany
    @JoinTable(name = "character_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<Character> character;



}


