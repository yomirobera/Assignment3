package no.accelerate.assignment3WebAPIandSpring.repositories;

import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Franchise repository interface extends JpaRepository
 * This gives standard CRUD operations for franchise table
 */
@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {


}
