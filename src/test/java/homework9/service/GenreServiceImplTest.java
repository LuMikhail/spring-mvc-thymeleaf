package homework9.service;

import homework9.domain.Genre;
import homework9.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class GenreServiceImplTest {

    @Configuration
    @Import({GenreServiceImpl.class})
    static class NestedTestConfiguration {
    }

    @MockBean
    private GenreRepository genreDao;

    @Autowired
    private GenreServiceImpl genreService;

    @Test
    void calledCorrectlyInsertGenre() {
        Genre genre = new Genre("Проза");
        genreService.insertGenre(new Genre("Проза"));
        verify(genreDao).save(genre);
    }

    @Test
    void calledCorrectlyFindAllGenres() {
        List<Genre> genres = new ArrayList<>();
        when(genreDao.findAll()).thenReturn(genres);
        List<Genre> resultGenres = genreService.findAllGenres();
        assertThat(resultGenres).isEqualTo(genres);
        verify(genreDao).findAll();
    }
}
