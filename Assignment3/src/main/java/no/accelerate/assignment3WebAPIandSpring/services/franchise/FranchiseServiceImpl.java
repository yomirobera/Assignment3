package no.accelerate.assignment3WebAPIandSpring.services.franchise;

import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.repositories.FranchiseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FranchiseServiceImpl implements FranchiseService {
    private final Logger logger = LoggerFactory.getLogger(FranchiseServiceImpl.class);
    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise findById(Integer id) {

        return franchiseRepository.findById(id).get();
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public Franchise update(Franchise entity) {

        return franchiseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }
    @Override
    public Movie getMovie(int franchise_id) {
        return (Movie) franchiseRepository.findById(franchise_id).get().getMovie();
    }

    /*
    //get a character in a franchise
    @Override
    public Character getCharacter(int franchise_id) {
        return (Movie) franchiseRepository.findById(franchise_id).get().getCharacter();
    }
     */

    @Override
    public boolean exists(Integer integer) {
        return false;
    }
}
