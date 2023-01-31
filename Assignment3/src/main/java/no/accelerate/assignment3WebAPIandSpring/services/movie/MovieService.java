package no.accelerate.assignment3WebAPIandSpring.services.movie;

import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.services.CrudService;

import java.util.Collection;

public interface MovieService extends CrudService<Movie, Integer> {

    /*
    Collection<Movie> findAllByName(String name);
    Collection<Character> getCharacters(int movieId);
     */
}
