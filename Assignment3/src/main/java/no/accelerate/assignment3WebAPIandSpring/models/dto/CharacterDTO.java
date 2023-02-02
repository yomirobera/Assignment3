package no.accelerate.assignment3WebAPIandSpring.models.dto;

//Data transfer objects

import lombok.Data;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;

import java.util.List;

@Data
public class CharacterDTO {
    private int id;
    private String alias;
    private String fullName;
    private String gender;
    private String picture;
    private List<Movie> movie;
}
