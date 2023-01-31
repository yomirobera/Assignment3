package no.accelerate.assignment3WebAPIandSpring.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "franchise")
    private List<Movie> movie;

    //Relation between franchise and character..

    /*
    @JsonGetter("movie")
    public Integer movieGetter() {
        if(movie == null)
            return null;
        return movie.getId();
    }
     */
}
