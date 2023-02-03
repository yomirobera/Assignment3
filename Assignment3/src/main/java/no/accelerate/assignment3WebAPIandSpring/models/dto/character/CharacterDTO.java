package no.accelerate.assignment3WebAPIandSpring.models.dto.character;

//Data transfer objects

import lombok.Data;
import java.util.Set;


@Data
public class CharacterDTO {
    private int id;
    private String alias;
    private String fullName;
    private String gender;
    private String picture;
    private Set<Integer> movie;
}
