package no.accelerate.assignment3WebAPIandSpring.services.movie;

import no.accelerate.assignment3WebAPIandSpring.exceptions.MovieNotFoundException;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MovieServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //@Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(()
                -> new MovieNotFoundException(id));
    }


}
