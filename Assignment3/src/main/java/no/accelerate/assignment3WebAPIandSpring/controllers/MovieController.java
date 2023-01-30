package no.accelerate.assignment3WebAPIandSpring.controllers;

import no.accelerate.assignment3WebAPIandSpring.services.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/Movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //Finding all movies
    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    //Getting a character in movie
    @GetMapping("{id}/character")
    public ResponseEntity getCharacters(@PathVariable int id) {
        return ResponseEntity.ok(movieService.getCharacters(id));
    }

}
