package no.accelerate.assignment3WebAPIandSpring.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    //One-to-many relationship between franchises and movie
    @OneToMany(mappedBy = "franchise")
    private List<Movie> movies;

    //Getter and setters  ??
}
