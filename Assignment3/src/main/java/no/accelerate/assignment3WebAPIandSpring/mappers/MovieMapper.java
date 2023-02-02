package no.accelerate.assignment3WebAPIandSpring.mappers;


import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.models.dto.MovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "title", source = "title")
    MovieDTO movieToMovieDTO(Movie movie);
}
