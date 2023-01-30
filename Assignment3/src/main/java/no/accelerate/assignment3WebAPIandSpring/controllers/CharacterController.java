package no.accelerate.assignment3WebAPIandSpring.controllers;

import no.accelerate.assignment3WebAPIandSpring.services.character.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController is an annotation to define a RESTful API.
 */
@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {

        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

}
