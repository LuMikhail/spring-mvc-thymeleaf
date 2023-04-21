package homework9.service;


import homework9.domain.Book;
import homework9.domain.Comment;
import homework9.exception.EntityNotFoundException;
import homework9.repository.BookRepository;
import homework9.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book assignCommentToBook(Long bookId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
        comment.setBook(book);
        book.getComments().add(comment);
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksContainThisGenre(Long genreId) {
        return bookRepository.findByGenreId(genreId);
    }

    @Override
    public List<Book> findBooksContainThisAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }
}
