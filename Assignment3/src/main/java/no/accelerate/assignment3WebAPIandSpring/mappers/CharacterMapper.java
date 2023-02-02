package no.accelerate.assignment3WebAPIandSpring.mappers;

import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.dto.CharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterDTO characterTocharacterDTO(Character character);
}
