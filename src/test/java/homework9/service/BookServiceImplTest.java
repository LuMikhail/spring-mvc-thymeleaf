package homework9.service;

import homework9.domain.Author;
import homework9.domain.Book;
import homework9.domain.Genre;
import homework9.repository.AuthorRepository;
import homework9.repository.BookRepository;
import homework9.repository.CommentRepository;
import homework9.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceImplTest {

    @Configuration
    @Import({BookServiceImpl.class})
    static class NestedTestConfiguration {
    }

    @MockBean
    private CommentRepository commentDao;

    @MockBean
    private BookRepository bookDao;

    @MockBean
    private GenreRepository genreDao;

    @MockBean
    private AuthorRepository authorDao;

    @Autowired
    private BookService bookService;

    @Test
    void calledCorrectlyMethodInsertBook() {
        Book book = new Book("Скриба", null, null,3);
        bookService.saveBook(new Book("Скриба", null, null, 3));
        verify(bookDao).save(book);
    }

    @Test
    void calledCorrectlyMethodFindBookById() {
        bookService.findBookById(1L);
        verify(bookDao).findById(1L);
    }

    @Test
    void calledCorrectlyMethodFindAllBooks() {
        bookService.findAllBooks();
        verify(bookDao).findAll();
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisGenre() {
        Genre genre = new Genre(1);
        bookService.findBooksContainThisGenre(1L);
        verify((bookDao)).findByGenreId(genre.getId());
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisAuthor() {
        Author author = new Author(1);
        bookService.findBooksContainThisAuthor(1L);
        verify(bookDao).findByAuthorId(author.getId());
    }


    @Test
    void calledCorrectlyMethodDeleteBooksById() {
        bookService.deleteBookById(1L);
        verify(bookDao).deleteById(1L);
    }
}