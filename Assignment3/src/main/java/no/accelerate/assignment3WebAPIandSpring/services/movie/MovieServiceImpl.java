package no.accelerate.assignment3WebAPIandSpring.services.movie;

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
    public Collection<Character> getCharacters(int movieId) {
        //return CharacterRepository.findById(movieId).get().getCharacters();
        return null;
    }
    /*
    //Update character for a movie
    @Override
    public void updateCharacter(int movieId, int[] character) {
        Movie mov = movieRepository.findById(movieId).get();
        Set<Character> characterList = new HashSet<>();
        for (int id: character) {
            characterList.add(characterRepository.findById(id).get());
        }
        mov.setCharacter(characterList);
        movieRepository.save(mov);
    }
     */

}
