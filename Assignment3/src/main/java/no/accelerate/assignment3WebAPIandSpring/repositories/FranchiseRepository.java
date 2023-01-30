package no.accelerate.assignment3WebAPIandSpring.repositories;

import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Franchise repository interface extends JpaRepository
 * This gives standard CRUD operations for franchise table
 */
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
}
