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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findCommentById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(long bookId) {
        Book book = bookRepository
                .findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
        return book.getComments();
    }

    @Override
    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }
}
