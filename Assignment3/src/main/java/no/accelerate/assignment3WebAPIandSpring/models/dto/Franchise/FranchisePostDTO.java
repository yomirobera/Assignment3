package no.accelerate.assignment3WebAPIandSpring.models.dto.Franchise;

import lombok.Getter;
import lombok.Setter;

//Data transfer objects
@Getter
@Setter
public class FranchisePostDTO {
    private String name;
    private String description;
}