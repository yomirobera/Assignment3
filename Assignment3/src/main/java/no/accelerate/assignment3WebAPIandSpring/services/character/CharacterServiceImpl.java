package no.accelerate.assignment3WebAPIandSpring.services.character;

import no.accelerate.assignment3WebAPIandSpring.repositories.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CharacterServiceImpl implements CharacterService{

    private final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);
    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }


    @Override
    public Character findById(Integer integer) {

        return null;
    }

    @Override
    public Collection<Character> findAll() {
        return null;
    }

    @Override
    public Character add(Character entity) {
        return null;
    }

    @Override
    public Character update(Character entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }
}
