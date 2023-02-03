package no.accelerate.assignment3WebAPIandSpring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

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
    private Set<Movie> movie;

    /*
    //Data access - cyclic reference
    @JsonGetter("movie")
    public List<Integer> jsonGetMovie() {
        if(movie !=null)
            return movie.stream().map(s->s.getId())
                    .collect(Collectors.toList());
        return null;
    }

    //Relation between franchise and character..
    @JsonGetter("movie")
    public Integer movieGetter() {
        if(movie == null)
            return null;
        return movie.getId();
    }
     */
}
