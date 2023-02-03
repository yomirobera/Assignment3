package no.accelerate.assignment3WebAPIandSpring.models.dto.character;

import lombok.Getter;
import lombok.Setter;

//Data transfer objects
@Getter
@Setter
public class CharacterPostDTO {
    private String fullName;
    private String alias;
    private String gender;
    private String picture;
}