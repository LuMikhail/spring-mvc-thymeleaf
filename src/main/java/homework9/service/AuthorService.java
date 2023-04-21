package homework9.service;

import homework9.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void saveAuthor(Author author);

    List<Author> findAllAuthors();

    Optional<Author> findAuthorById(long id);

    void deleteAuthorById(long id);
}
