package no.accelerate.assignment3WebAPIandSpring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.assignment3WebAPIandSpring.mappers.CharacterMapper;
import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.services.character.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * @RestController is an annotation to define a RESTful API.
 */
@RestController
@RequestMapping(path = "api/v1/character")
public class CharacterController {
    private final CharacterService characterService;

    private final CharacterMapper characterMapper;
    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {

        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    //Get a collection of characters
    @GetMapping
    @Operation(summary = "Gets all the Characters")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Character.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    public ResponseEntity<Collection<Character>> getAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

    //Find by id

    @GetMapping("{id}")
    @Operation(summary = "Gets a character based on id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Character.class))

                    }
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
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(characterService.findById(id));
    }

    //Add character
    @PostMapping
    @Operation(summary = "Adds a new character")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })
    public ResponseEntity add(@RequestBody Character entity) throws URISyntaxException {
        //Add franchise
        characterService.add(entity);
        URI uri = new URI ("api/v1/character/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    //Update character
    @PutMapping("{id}")
    @Operation(summary = "Updates a character with a given id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
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

    public ResponseEntity update(@RequestBody Character entity)  {
        characterService.update(entity);
        return ResponseEntity.noContent().build();
    }

    //Delete a character
    @DeleteMapping("{id}")
    @Operation(summary = "Deletes a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content =  {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Character.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content)
    })
    public ResponseEntity delete(@PathVariable int id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
