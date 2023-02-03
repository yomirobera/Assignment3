package no.accelerate.assignment3WebAPIandSpring.services.franchise;

import no.accelerate.assignment3WebAPIandSpring.exceptions.FranchiseNotFoundException;
import no.accelerate.assignment3WebAPIandSpring.exceptions.MovieNotFoundException;
import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.repositories.FranchiseRepository;
import no.accelerate.assignment3WebAPIandSpring.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FranchiseServiceImpl implements FranchiseService {
    private final Logger logger = LoggerFactory.getLogger(FranchiseServiceImpl.class);
    private final FranchiseRepository franchiseRepository;
    private final MovieRepository movieRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, MovieRepository movieRepository) {
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow(()
                -> new FranchiseNotFoundException(id));
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
       franchiseRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Collection<Movie> getMovie(int franchise_id) {
        return franchiseRepository.findById(franchise_id).get().getMovie();
    }

    /**
     * Updates movies in a franchise by franchise_id and a list of movie ids.
     * Loops through the movie ids and adds them to a set of movies.
     * Saves the franchise and returns the movies.
     */
    @Override
    public Set<Movie> updateMovies(int franchise_id, int[] movies){
        Franchise franchise = franchiseRepository.findById(franchise_id).orElseThrow(() -> new FranchiseNotFoundException(franchise_id));

        Set<Movie> validMovies = new HashSet<>();
        for (int movie_id : movies){
            Movie m = movieRepository.findById(movie_id).orElseThrow(() -> new MovieNotFoundException(movie_id));
            validMovies.add(m);
        }

        for (Movie m : validMovies){
            m.setFranchise(franchise);
        }

        franchiseRepository.save(franchise);

        return validMovies;
    }

    @Override
    public Set<Movie> getAllMovies(int franchise_id) {
        Franchise franchise = franchiseRepository.findById(franchise_id).orElseThrow(() -> new FranchiseNotFoundException(franchise_id));
        return franchise.getMovie();
    }


    /**
     * Gets all characters in a franchise by franchise_id.
     * Calls getAllMovies() to get all movies in a franchise, and then loops through
     * the movies and adds all their characters to a list, and returns it.
     */
    public List<Character> getCharacters(int franchise_id){
        Set<Movie> movies = getAllMovies(franchise_id);
        List<Character> characters = new ArrayList<>();

        for (Movie m : movies){
            characters.addAll(m.getCharacter());
        }

        return characters;
    }
}
