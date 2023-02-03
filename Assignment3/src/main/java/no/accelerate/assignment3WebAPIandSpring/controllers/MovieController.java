package no.accelerate.assignment3WebAPIandSpring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.assignment3WebAPIandSpring.exceptions.error.ApiErrorResponse;
import no.accelerate.assignment3WebAPIandSpring.mappers.MovieMapper;
import no.accelerate.assignment3WebAPIandSpring.models.Character;
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
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }


    //Finding all movies
    @GetMapping // GET: localhost:8080/api/v1/movie
    @Operation(summary = "Gets all the Movies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    public ResponseEntity<Collection<Movie>>getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movie/1
    @Operation(summary = "Gets a movies based on id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(
        movieMapper.movieToMovieDTO(
                movieService.findById(id)
        ));

    }

    //Adding a movie
    @PostMapping
    @Operation(summary = "Adds a new movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })

    public ResponseEntity add(@RequestBody Movie entity) throws URISyntaxException {
        //Add franchise
        movieService.add(entity);
        URI uri = new URI ("api/v1/movie/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    //Update Movie
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/movie/1
    @Operation(summary = "Update a Movie with a given id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    public ResponseEntity update(@RequestBody Movie entity)  {
        movieService.update(entity);
        return ResponseEntity.noContent().build();
    }

    //Delete a Movie
    @DeleteMapping("{id}")
    @Operation(summary = "Deletes a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Success",
                    content =  {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content)
    })
    public ResponseEntity delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

   //Character in a movie based a given id.
    @GetMapping("{id}/character") //http://localhost:8080/api/v1/movie/1/character
    @Operation(summary = "Get character in a movie based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content =  {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })

    public ResponseEntity <Collection<Character>>getAllCharacter(@PathVariable int id) {
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
