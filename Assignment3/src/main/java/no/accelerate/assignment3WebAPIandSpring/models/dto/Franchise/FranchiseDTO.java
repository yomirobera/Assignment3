package no.accelerate.assignment3WebAPIandSpring.models.dto.Franchise;


import lombok.Data;
import java.util.Set;

//Data transfer objects
@Data
public class FranchiseDTO {
    private Integer id;
    private String name;
    private String description;
    private Set<Integer> movie;
}
