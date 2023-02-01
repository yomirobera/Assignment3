package no.accelerate.assignment3WebAPIandSpring.controllers;

import no.accelerate.assignment3WebAPIandSpring.models.Character;
import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
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

    public CharacterController(CharacterService characterService) {

        this.characterService = characterService;
    }

    //Get a collection of characters
    @GetMapping
    public ResponseEntity<Collection<Character>> getAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

    //Find by id
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(characterService.findById(id));
    }

    //Add character
    @PostMapping
    public ResponseEntity add(@RequestBody Character entity) throws URISyntaxException {
        //Add franchise
        characterService.add(entity);
        URI uri = new URI ("api/v1/character/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    //Update character
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Character entity)  {
        characterService.update(entity);
        return ResponseEntity.noContent().build();
    }



}
