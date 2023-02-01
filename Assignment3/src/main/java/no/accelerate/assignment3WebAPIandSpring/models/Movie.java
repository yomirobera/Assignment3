package no.accelerate.assignment3WebAPIandSpring.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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




    //many-to-one relationship between franchises and movie
    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonGetter("franchise")
    public Integer jsonGetFranchise() {
        if(franchise !=null)
            return franchise.getId();
        return null;
    }
    /**
     * Many-to-many relationship between character and movie entities
     * The relationship is called "character_movie"
     */
    @ManyToMany
    @JoinTable(name = "character_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<Character> character;

    //Data access - cyclic reference
    @JsonGetter("character")
    public List<Integer> jsonGetCharacter() {
        if(character !=null)
            return character.stream().map(s->s.getId())
                    .collect(Collectors.toList());
        return null;
    }



}


