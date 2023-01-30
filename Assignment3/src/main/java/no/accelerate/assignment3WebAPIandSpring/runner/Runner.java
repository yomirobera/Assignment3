package no.accelerate.assignment3WebAPIandSpring.runner;

import no.accelerate.assignment3WebAPIandSpring.services.character.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Runner implements CommandLineRunner {

    private final CharacterService characterService;

    public Runner(CharacterService characterService) {

        this.characterService = characterService;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
