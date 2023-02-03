package no.accelerate.assignment3WebAPIandSpring.services.movie;

import no.accelerate.assignment3WebAPIandSpring.exceptions.CharacterNotFoundException;
import no.accelerate.assignment3WebAPIandSpring.exceptions.MovieNotFoundException;
import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.repositories.CharacterRepository;
import no.accelerate.assignment3WebAPIandSpring.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService{
    private final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;

    private final CharacterRepository characterRepository;

    public MovieServiceImpl(MovieRepository movieRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }


    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(()
                -> new MovieNotFoundException(id));
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }


    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }


    @Override
    public Collection<Character> getCharacters(int movie_id) {
        return movieRepository.findById(movie_id).get().getCharacter();

    }

    /**
     * Updates characters in a movie by a movie_id and a list of character ids.
     * Loops through the character ids and adds them to a set of characters.
     * Saves the movie and returns the characters.
     */
    @Override
    public Set<Character> updateCharacters(int movie_id, int[] characters){
        Movie movie = movieRepository.findById(movie_id).orElseThrow(() -> new MovieNotFoundException(movie_id));

        Set<Character> validCharacters = new HashSet<>();
        for (int character_id : characters){
            Character c = characterRepository.findById(character_id).orElseThrow(() -> new CharacterNotFoundException(character_id));
            validCharacters.add(c);
        }

        movie.setCharacter(validCharacters);
        movieRepository.save(movie);

        return validCharacters;
    }


}
