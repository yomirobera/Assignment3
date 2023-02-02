package no.accelerate.assignment3WebAPIandSpring.models.dto;


import lombok.Data;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;

import java.util.List;

@Data
public class FranchiseDTO {
    private Integer id;
    private String name;
    private String description;
    private List <Movie> movie;
}
