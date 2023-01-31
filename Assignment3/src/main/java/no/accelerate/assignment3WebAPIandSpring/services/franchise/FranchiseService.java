package no.accelerate.assignment3WebAPIandSpring.services.franchise;

import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.repositories.FranchiseRepository;
import no.accelerate.assignment3WebAPIandSpring.services.CrudService;


public interface FranchiseService extends CrudService<Franchise, Integer> {
    Movie getMovie(int franchise_id);
    //Character getCharacter(int franchise_id);

}
