package no.accelerate.assignment3WebAPIandSpring.exceptions;

public class MovieNotFoundException extends EntityNotFoundException{
    public MovieNotFoundException(int id) {
        super("Movie does not exist with id" +id);
    }
}
