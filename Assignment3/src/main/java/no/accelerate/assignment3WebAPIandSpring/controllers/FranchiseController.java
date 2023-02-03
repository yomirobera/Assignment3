package no.accelerate.assignment3WebAPIandSpring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.assignment3WebAPIandSpring.exceptions.error.ApiErrorResponse;
import no.accelerate.assignment3WebAPIandSpring.mappers.CharacterMapper;
import no.accelerate.assignment3WebAPIandSpring.mappers.FranchiseMapper;
import no.accelerate.assignment3WebAPIandSpring.mappers.MovieMapper;
import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Franchise.FranchiseDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Franchise.FranchisePostDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Movie.MovieDTO;
import no.accelerate.assignment3WebAPIandSpring.services.franchise.FranchiseService;
import no.accelerate.assignment3WebAPIandSpring.services.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final MovieService movieService;
    //Franchise Mapper
    private final FranchiseMapper franchiseMapper;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;

    public FranchiseController(FranchiseService franchiseService, MovieService movieService, FranchiseMapper franchiseMapper, MovieMapper movieMapper, CharacterMapper characterMapper) {

        this.franchiseService = franchiseService;
        this.movieService = movieService;
        this.franchiseMapper = franchiseMapper;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }

    /**
     * Gets all characters as DTOs. Returns appropriate response code.
     */
    @GetMapping //http://localhost:8080/api/v1/franchise
    @Operation(summary = "Gets all the franchises")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    public ResponseEntity getAll() {
        return ResponseEntity.ok(
                franchiseMapper.franchiseToFranchiseDTO(
                        franchiseService.findAll()
                ));
    }

    /**
     * Finds a franchise as DTO by its id. Returns appropriate response code.
     */
    @GetMapping("{id}") // GET: http://localhost:8080/api/v1/franchise/1
    @Operation(summary = "Get a franchise based on id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = FranchiseDTO.class))

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
                franchiseMapper.franchiseToFranchiseDTO(
                        franchiseService.findById(id)
                ));

    }

    /**
     * Adds a new franchise as DTO. Returns appropriate response code.
     */
    @PostMapping
    @Operation(summary = "Adds a new franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FranchisePostDTO.class))
                    }),

    })
    public ResponseEntity add(@RequestBody Franchise entity) throws URISyntaxException {
        //Add franchise
        franchiseMapper.franchiseToFranchisePostDTO(franchiseService.add(entity));
        URI uri = new URI ("api/v1/franchise/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    /**
     * Updates a franchise as DTO with a given id. Calls update() from FranchiseService.
     * Returns appropriate response code.
     */
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/franchise/1
    @Operation(summary = "Updates a franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content),

            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content)
    })
    public ResponseEntity update(@RequestBody Franchise entity, @PathVariable int id)  {
        //Avoid invalid request
        if (id != entity.getId())
                return ResponseEntity.badRequest().build();
        franchiseMapper.franchiseToFranchisePutDTO(franchiseService.update(entity));
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a franchise by its id. Calls deleteById() from FranchiseService.
     * Returns appropriate response code.
     */
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/Franchise/1
    @Operation(summary = "Deletes a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                         description = "No content",
                         content = @Content
            ),
            @ApiResponse(responseCode = "404",
                        description = "Not Found",
                        content =  @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FranchiseDTO.class))),
            })
    public ResponseEntity delete(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Gets movies as DTOs from a franchise by the id of the franchise.
     * Returns appropriate response code.
     */
    @Operation(summary = "Gets movies in franchise by id")
            @ApiResponse(responseCode = "200",
                        description = "Success",
                        content = {@Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))

    })
    @GetMapping("{id}/movie") // Get: localhost:8080/api/v1/franchise/1/movie
    public ResponseEntity getAllMovie(@PathVariable int id) {
        return ResponseEntity.ok(
                movieMapper.movieToMovieDTO(
                        franchiseService.findById(id).getMovie()));
    }

    /**
     * Updates movies in a franchise by id.
     * Returns appropriate response code.
     */
    @PutMapping("{id}/movie")
    @Operation(summary = "Updates movies in franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content =  @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            ),
    })
    public ResponseEntity updateMovies(@PathVariable int id, @RequestBody int[] movies){
        movieMapper.movieToMoviePutDTO(franchiseService.updateMovies(id, movies));
        return ResponseEntity.noContent().build();
    }

    /**
     * Gets characters as DTOs from a franchise by the id of the franchise.
     * Calls getCharacters() in franchiseService, which loops over movies in a franchise
     * and characters in a movie to find characters in a franchise.
     * Returns appropriate response code.
     */
    @Operation(summary = "Gets characters from franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = FranchiseDTO.class)
                    )),

            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })

    //Gets characters form franchise
    @GetMapping("{id}/character")
    public ResponseEntity getCharacters(@PathVariable int id) {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDTO(
                        franchiseService.getCharacters(id)
                ));
    }
}
