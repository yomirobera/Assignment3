package no.accelerate.assignment3WebAPIandSpring.mappers;


import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Movie.MovieDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Movie.MoviePostDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Movie.MoviePutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    /**
     *@Mapping to convert the integers to domain objects
     */
    @Mapping(target = "franchise", source = "franchise.id")
    @Mapping(target = "character", qualifiedByName = "charactersToIds")
    public abstract MovieDTO movieToMovieDTO(Movie movie);

    public abstract Collection<MovieDTO> movieToMovieDTO(Collection<Movie> movies);
    public abstract MoviePostDTO movieToMoviePostDTO(Movie movies);
    public abstract MoviePutDTO movieToMoviePutDTO(Movie movies);
    public abstract Collection<MoviePutDTO> movieToMoviePutDTO(Collection<Movie> movies);


    @Named("charactersToIds")
    Set<Integer> map(Set<Character> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(Character::getId).collect(Collectors.toSet());
    }
}
