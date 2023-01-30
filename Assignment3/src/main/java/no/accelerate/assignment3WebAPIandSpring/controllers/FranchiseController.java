package no.accelerate.assignment3WebAPIandSpring.controllers;

import no.accelerate.assignment3WebAPIandSpring.services.franchise.FranchiseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private final FranchiseService franchiseService;


    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }
}
