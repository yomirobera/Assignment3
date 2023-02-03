package no.accelerate.assignment3WebAPIandSpring.models.dto;

import lombok.Data;
import no.accelerate.assignment3WebAPIandSpring.models.Character;

import java.util.List;
import java.util.Set;

@Data
public class MovieDTO {
    private Integer id;

    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String picture;
    private String trailer;
    private int franchise_id;
    private Set<Character> character;


}
