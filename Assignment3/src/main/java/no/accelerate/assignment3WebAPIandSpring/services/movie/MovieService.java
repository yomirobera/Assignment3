package no.accelerate.assignment3WebAPIandSpring.services.movie;

import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.services.CrudService;

import java.util.Collection;
import java.util.Set;

public interface MovieService extends CrudService<Movie, Integer> {

    Collection<Character> getCharacters(int movieId);
    public Set<Character> updateCharacters(int movie_id, int[] characters);
}
