package no.accelerate.assignment3WebAPIandSpring.repositories;

import no.accelerate.assignment3WebAPIandSpring.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Character interface extends JpaRepository
 * This gives standard CRUD operations for character table
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
