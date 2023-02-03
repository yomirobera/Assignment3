package no.accelerate.assignment3WebAPIandSpring.models.dto.Movie;

import lombok.Data;
import java.util.Set;

//Data transfer objects
@Data
public class MovieDTO {
    private Integer id;

    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String picture;
    private String trailer;
    private Integer franchise;
    private Set<Integer> character;


}
