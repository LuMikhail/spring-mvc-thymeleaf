package homework9.service;

import homework9.domain.Genre;

import java.util.List;

public interface GenreService {

    void insertGenre(Genre genre);

    List<Genre> findAllGenres();
}
