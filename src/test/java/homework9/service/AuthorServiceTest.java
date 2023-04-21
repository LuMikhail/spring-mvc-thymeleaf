package homework9.service;

import homework9.domain.Author;
import homework9.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class AuthorServiceTest {

    @Configuration
    @Import(AuthorServiceImpl.class)
    static class NestedTestConfiguration {
    }

    @MockBean
    private AuthorRepository authorDao;

    @Autowired
    AuthorService authorService;

    @Test
    void calledCorrectlyInsertAuthor() {
        Author author = new Author("Джош Блох");
        authorService.saveAuthor(new Author("Джош Блох"));
        verify(authorDao).save(author);
    }

    @Test
    void calledCorrectlyFindAllAuthors() {
        authorService.findAllAuthors();
        verify(authorDao).findAll();
    }
}