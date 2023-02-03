package no.accelerate.assignment3WebAPIandSpring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.assignment3WebAPIandSpring.exceptions.error.ApiErrorResponse;
import no.accelerate.assignment3WebAPIandSpring.mappers.FranchiseMapper;
import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.models.dto.FranchiseDTO;
import no.accelerate.assignment3WebAPIandSpring.services.franchise.FranchiseService;
import no.accelerate.assignment3WebAPIandSpring.services.movie.MovieService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final MovieService movieService;
    //Franchise Mapper
    private final FranchiseMapper franchiseMapper;

    public FranchiseController(FranchiseService franchiseService, MovieService movieService, FranchiseMapper franchiseMapper) {

        this.franchiseService = franchiseService;
        this.movieService = movieService;
        this.franchiseMapper = franchiseMapper;
    }

    //Get a collection of franchises
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
    public ResponseEntity findAll() {
        return ResponseEntity.ok(franchiseService.findAll());
    }

    //Get a franchise by id
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

    //Adding a franchise
    @PostMapping
    @Operation(summary = "Adds a new franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) //FranchisePostDTO
                    }),

    })
    public ResponseEntity add(@RequestBody Franchise entity) throws URISyntaxException {
        //Add franchise
        franchiseService.add(entity);
        URI uri = new URI ("api/v1/franchise/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    //Update a franchise
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
            franchiseService.update(entity);
        return ResponseEntity.noContent().build();
    }

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
                                    schema = @Schema(implementation = Franchise.class))),
            })
    public ResponseEntity delete(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Movie in a franchise
    @Operation(summary = "Gets movies in franchise by id")
            @ApiResponse(responseCode = "200",
                        description = "Success",
                        content = {@Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Movie.class)))

    })
    @GetMapping("{id}/movie") // Get: localhost:8080/api/v1/franchise/1/movie
    public ResponseEntity <Collection<Movie>>getAllMovie(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.findById(id).getMovie());
    }

    //Updates movie in a franchise
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
        franchiseService.updateMovies(id, movies);
        return ResponseEntity.noContent().build();
    }
    /*
    @Operation(summary = "Get all characters in franchise")
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
                            schema=@Schema(implementation = Franchise.class)
                    )),

            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = Franchise.class)
                    )),

    })

     */

}
