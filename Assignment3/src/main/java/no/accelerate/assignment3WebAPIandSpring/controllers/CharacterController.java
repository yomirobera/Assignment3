package no.accelerate.assignment3WebAPIandSpring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.assignment3WebAPIandSpring.exceptions.error.ApiErrorResponse;
import no.accelerate.assignment3WebAPIandSpring.mappers.CharacterMapper;
import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.dto.character.CharacterDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.character.CharacterPostDTO;
import no.accelerate.assignment3WebAPIandSpring.services.character.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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
    /**
     * Gets all characters as DTOs. Returns appropriate response code.
     */
    @GetMapping
    @Operation(summary = "Gets all the Characters")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    public ResponseEntity getAll() {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDTO(
                        characterService.findAll()
                ));
    }

    /**
     * Finds a character as DTO by its id. Returns appropriate response code.
     */
    @GetMapping("{id}")
    @Operation(summary = "Gets a character based on id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))

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
                characterMapper.characterToCharacterDTO(
                        characterService.findById(id)
                ));

    }

    /**
     * Adds a new character as DTO. Returns appropriate response code.
     */
    //Add character
    @PostMapping
    @Operation(summary = "Adds a new character")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CharacterPostDTO.class))
                    })

    })
    public ResponseEntity add(@RequestBody Character entity) throws URISyntaxException {
        //Add franchise
        characterMapper.characterToCharacterPostDTO(characterService.add(entity));
        URI uri = new URI ("api/v1/character/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    /**
     * Updates a character as DTO with a given id. Calls update() from CharacterService.
     * Returns appropriate response code.
     */
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/character/1
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
        characterMapper.characterToCharacterPutDTO(characterService.update(entity));
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a character by its id. Calls deleteById() from CharacterService.
     * Returns appropriate response code.
     */
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
        return ResponseEntity.ok().build();
    }

}
