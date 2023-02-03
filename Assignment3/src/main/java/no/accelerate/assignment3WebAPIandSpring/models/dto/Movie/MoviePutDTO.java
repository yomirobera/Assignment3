package no.accelerate.assignment3WebAPIandSpring.models.dto.Movie;

import lombok.Getter;
import lombok.Setter;

//Data transfer objects
@Getter
@Setter
public class MoviePutDTO {

    private Integer id;
    private String title;
    private String genre;
    private Integer releaseYear;
    private String director;
    private String picture;
    private String trailer;
}