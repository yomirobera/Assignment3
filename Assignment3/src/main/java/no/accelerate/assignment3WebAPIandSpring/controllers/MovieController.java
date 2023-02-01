package no.accelerate.assignment3WebAPIandSpring.controllers;

import com.fasterxml.jackson.annotation.JsonGetter;
import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.services.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    //Finding all movies
    @GetMapping
    public ResponseEntity<Collection<Movie>>getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    //Adding a movie
    @PostMapping
    public ResponseEntity add(@RequestBody Movie entity) throws URISyntaxException {
        //Add franchise
        movieService.add(entity);
        URI uri = new URI ("api/v1/movie/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    //Update Movie
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Movie entity)  {
        movieService.update(entity);
        return ResponseEntity.noContent().build();
    }

    //Movie in a franchise
    @GetMapping("{id}/character")
    public ResponseEntity <Collection<Character>>getAllMovie(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id).getCharacter());
    }

    /*
    //Update character for a movie
    @PutMapping("{id}/character")
    public ResponseEntity updateCharacter(@PathVariable int id, @RequestBody int[] CharacterIds) {
        movieService.updateCharacter(id, CharacterIds);
        return ResponseEntity.noContent().build();
    }
     */

}
