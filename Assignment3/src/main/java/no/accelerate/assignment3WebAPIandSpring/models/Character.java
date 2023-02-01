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
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String fullName;
    @Column(length = 50, nullable = false)
    private String alias;
    private String gender;
    private String picture;


    //Data access - cyclic reference
    @JsonGetter("movie")
    public List<Integer> jsonGetMovie() {
        if(movie !=null)
            return movie.stream().map(s->s.getId())
                    .collect(Collectors.toList());
        return null;
    }

    //Many-to-many relationship between character and movie entities
    @ManyToMany(mappedBy ="character")
    private List<Movie> movie;



}
