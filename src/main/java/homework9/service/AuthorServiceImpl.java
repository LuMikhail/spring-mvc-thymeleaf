package homework9.service;

import homework9.domain.Author;
import homework9.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findAuthorById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    public void deleteAuthorById(long id) {
        authorRepository.deleteById(id);
    }
}
