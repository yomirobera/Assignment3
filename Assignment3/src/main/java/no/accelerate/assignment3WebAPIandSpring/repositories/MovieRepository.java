package no.accelerate.assignment3WebAPIandSpring.repositories;

import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Set;

/**
 * Movie repository interface extends JpaRepository
 * This gives standard CRUD operations for Movie table
 */
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Collection<Movie> findAllByName(String name);

    //@Query("select m from Movie m where m.name like %?1%")
    //Set<Movie> findAllByName(String name);
}
