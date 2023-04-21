package homework9.service;


import homework9.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void saveBook(Book book);

    Book assignCommentToBook(Long bookId, Long commentId);

    Optional<Book> findBookById(long id);

    List<Book> findAllBooks();

    List<Book> findBooksContainThisGenre(Long genre);

    List<Book> findBooksContainThisAuthor(Long author);

    void deleteBookById(long id);

}
