package edu.school21.cinema.models;

import lombok.Data;

@Data
public class Film {
    private Long id;
    private String name;
    private String posterUrl;

    public Film(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getTitle();
        this.posterUrl = movie.getPosterUrl();
    }
}
