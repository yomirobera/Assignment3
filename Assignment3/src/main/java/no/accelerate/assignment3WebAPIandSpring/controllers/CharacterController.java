package no.accelerate.assignment3WebAPIandSpring.controllers;

import no.accelerate.assignment3WebAPIandSpring.services.character.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public CharacterController(CharacterService characterService) {

        this.characterService = characterService;
    }

    //Get a collection of characters
    @GetMapping
    public ResponseEntity<Collection<Character>> getAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

    //

}
