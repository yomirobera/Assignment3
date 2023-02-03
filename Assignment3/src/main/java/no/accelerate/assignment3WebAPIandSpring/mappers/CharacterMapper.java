package no.accelerate.assignment3WebAPIandSpring.mappers;

import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.models.dto.character.CharacterDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.character.CharacterPostDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.character.CharacterPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

    /**
     *@Mapping to convert the integers to domain objects
     */
    @Mapping(target = "movie", qualifiedByName = "moviesToIds")
    public abstract CharacterDTO characterToCharacterDTO(Character character);

    public abstract Collection<CharacterDTO> characterToCharacterDTO(Collection<Character> characters);
    public abstract CharacterPostDTO characterToCharacterPostDTO(Character character);
    public abstract Collection<CharacterPutDTO> characterToCharacterPutDTO(Collection<Character> character);
    public abstract CharacterPutDTO characterToCharacterPutDTO(Character character);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream()
                .map(Movie::getId).collect(Collectors.toSet());
    }

}
