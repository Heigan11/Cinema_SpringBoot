package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int yearOfRelease;

    @Column(name = "restriction")
    private int ageRestrictions;

    @Column(name = "description")
    private String description;

    @Column(name = "poster")
    private String posterUrl;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Session> sessions;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Message> messages;
}
