package homework9.service;

import homework9.domain.Genre;
import homework9.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public void insertGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
}

