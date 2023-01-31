package no.accelerate.assignment3WebAPIandSpring.models.dto.movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
    private Integer id;

    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String picture;
    private String trailer;
}
