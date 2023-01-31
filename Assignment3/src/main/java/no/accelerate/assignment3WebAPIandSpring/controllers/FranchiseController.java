package no.accelerate.assignment3WebAPIandSpring.controllers;

import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.services.franchise.FranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {
    private final FranchiseService franchiseService;


    public FranchiseController(FranchiseService franchiseService) {

        this.franchiseService = franchiseService;
    }

    //Get a collection of franchises
    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(franchiseService.findAll());
    }

    //Get a franchise by id
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.findById(id));
    }

    //Adding a franchise
    @PostMapping
    public ResponseEntity add(@RequestBody Franchise entity) throws URISyntaxException {
        //Add franchise
        franchiseService.add(entity);
        URI uri = new URI ("api/v1/franchise/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    //Update a franchise
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Franchise entity)  {
            franchiseService.update(entity);
        return ResponseEntity.noContent().build();
    }

    //Movie in a franchise (Currently not working)
    @GetMapping("{id}/movie")
    public ResponseEntity getMovie(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.getMovie(id));
    }
    //Characters in a franchise
    /*
    @GetMapping("{id}/character")
    public ResponseEntity getCharacter(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.getCharacter(id));
    }
     */


}
