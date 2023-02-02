package no.accelerate.assignment3WebAPIandSpring.mappers;

import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.dto.FranchiseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
    FranchiseDTO franchiseToFranchiseDTO(Franchise franchise);
}
