package no.accelerate.assignment3WebAPIandSpring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String fullName;
    @Column(length = 50, nullable = false)
    private String alias;
    private String gender;
    private String picture;

    //Many-to-many relationship between character and movie entities
    @ManyToMany(mappedBy ="character")
    private List<Movie> movie;



}
