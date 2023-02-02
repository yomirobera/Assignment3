package no.accelerate.assignment3WebAPIandSpring.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import no.accelerate.assignment3WebAPIandSpring.models.Character;

import java.util.List;
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
    private List<Character> character;


}
