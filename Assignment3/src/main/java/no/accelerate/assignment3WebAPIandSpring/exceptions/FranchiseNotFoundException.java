package no.accelerate.assignment3WebAPIandSpring.exceptions;

public class FranchiseNotFoundException extends EntityNotFoundException {

    public FranchiseNotFoundException(int id) {
        super("Franchise does not exist with id"+ id);
    }
}
