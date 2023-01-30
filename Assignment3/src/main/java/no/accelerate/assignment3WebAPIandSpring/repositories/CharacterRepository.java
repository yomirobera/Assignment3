package no.accelerate.assignment3WebAPIandSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Character interface extends JpaRepository
 * This gives standard CRUD operations for character table
 */
public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
