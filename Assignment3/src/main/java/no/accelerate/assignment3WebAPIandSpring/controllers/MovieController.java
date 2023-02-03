package no.accelerate.assignment3WebAPIandSpring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.assignment3WebAPIandSpring.exceptions.error.ApiErrorResponse;
import no.accelerate.assignment3WebAPIandSpring.mappers.CharacterMapper;
import no.accelerate.assignment3WebAPIandSpring.mappers.MovieMapper;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Movie.MovieDTO;
import no.accelerate.assignment3WebAPIandSpring.services.movie.MovieService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;


    public MovieController(MovieService movieService, MovieMapper movieMapper, CharacterMapper characterMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }


    /**
     * Gets all movies as DTOs. Returns appropriate response code.
     */
    @GetMapping // GET: localhost:8080/api/v1/movie
    @Operation(summary = "Gets all the Movies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    public ResponseEntity getAll() {
        return ResponseEntity.ok(
                movieMapper.movieToMovieDTO(
                        movieService.findAll()
                ));
    }

    /**
     * Finds a movie as DTO by its id. Returns appropriate response code.
     */
    @GetMapping("{id}") // GET: localhost:8080/api/v1/movie/1
    @Operation(summary = "Gets a movies based on id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))

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

    /**
     * Adds a new movie as DTO. Returns appropriate response code.
     */
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
        movieMapper.movieToMoviePostDTO(movieService.add(entity));
        URI uri = new URI ("api/v1/movie/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    /**
     * Updates a movie as DTO with a given id. Calls update() from MovieService.
     * Returns appropriate response code.
     */
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
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)) })
    })
    public ResponseEntity update(@RequestBody Movie entity)  {
        movieMapper.movieToMoviePutDTO(movieService.update(entity));
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a movie by its id. Calls deleteById() from MovieService.
     * Returns appropriate response code.
     */
    @DeleteMapping("{id}")
    @Operation(summary = "Deletes a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content =  {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content)
    })
    public ResponseEntity delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Gets character in a movie as DTOs by the id of the movie.
     * Returns appropriate response code.
     */
    @GetMapping("{id}/character") //http://localhost:8080/api/v1/movie/1/character
    @Operation(summary = "Get character in a movie based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content =  {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    public ResponseEntity getAllCharacter(@PathVariable int id) {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDTO(
                        movieService.findById(id).getCharacter()
                ));
    }

    /**
     * Updates characters in a movie by id.
     * Returns appropriate response code.
     */
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
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)) })
    })
    //Updates characters in a movie.
    @PutMapping("{id}/character")
    public ResponseEntity updateCharacters(@PathVariable int id, @RequestBody int[] characters){
        characterMapper.characterToCharacterPutDTO(movieService.updateCharacters(id, characters));
        return ResponseEntity.noContent().build();
    }

}
