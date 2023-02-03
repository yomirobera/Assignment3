package no.accelerate.assignment3WebAPIandSpring.mappers;

import no.accelerate.assignment3WebAPIandSpring.models.Franchise;
import no.accelerate.assignment3WebAPIandSpring.models.Movie;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Franchise.FranchiseDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Franchise.FranchisePostDTO;
import no.accelerate.assignment3WebAPIandSpring.models.dto.Franchise.FranchisePutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {

    /**
     *@Mapping to convert the integers to domain objects
     */
    @Mapping(target = "movie", qualifiedByName = "moviesToIds")
    public abstract FranchiseDTO franchiseToFranchiseDTO(Franchise franchise);
    public abstract Collection<FranchiseDTO> franchiseToFranchiseDTO(Collection<Franchise> franchises);
    public abstract FranchisePostDTO franchiseToFranchisePostDTO(Franchise franchise);
    public abstract FranchisePutDTO franchiseToFranchisePutDTO(Franchise franchise);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream()
                .map(Movie::getId).collect(Collectors.toSet());
    }
}