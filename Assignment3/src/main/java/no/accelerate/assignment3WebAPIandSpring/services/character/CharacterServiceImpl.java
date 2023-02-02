package no.accelerate.assignment3WebAPIandSpring.services.character;

import no.accelerate.assignment3WebAPIandSpring.exceptions.CharacterNotFoundException;
import no.accelerate.assignment3WebAPIandSpring.exceptions.FranchiseNotFoundException;
import no.accelerate.assignment3WebAPIandSpring.models.Character;
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
    public Character findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(()
                -> new CharacterNotFoundException(id));
    }

    @Override
    public Collection<Character> findAll() {
       return characterRepository.findAll();

    }

    @Override
    public Character add(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public Character update(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        characterRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }
}
