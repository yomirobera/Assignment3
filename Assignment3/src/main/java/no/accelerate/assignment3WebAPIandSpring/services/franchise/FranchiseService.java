package no.accelerate.assignment3WebAPIandSpring.services.franchise;

import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.repositories.FranchiseRepository;
import no.accelerate.assignment3WebAPIandSpring.services.CrudService;

import java.util.Collection;


public interface FranchiseService extends CrudService<Franchise, Integer> {
    Collection<Movie> getMovie(int franchise_id);
    /*
    Movie getMovie(int franchise_id);
    //getting a character in a franchise
    Character getCharacter(int franchise_id);

    //Getting movies in a franchise
    //Collection<Movie> getMovie(int franchise_id);

     */

    public void updateMovies(int franchise_id, int[] movies);
}
