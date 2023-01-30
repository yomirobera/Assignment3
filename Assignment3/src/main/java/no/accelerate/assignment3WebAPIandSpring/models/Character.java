package no.accelerate.assignment3WebAPIandSpring.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private String alias;
    private String gender;
    private String picture;

    //Many-to-many relationship between character and movie entities
    @ManyToMany(mappedBy ="characters")
    private List<Movie> movies;


}
