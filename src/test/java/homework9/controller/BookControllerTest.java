package homework9.controller;

import homework9.domain.Author;
import homework9.domain.Book;
import homework9.domain.Comment;
import homework9.domain.Genre;
import homework9.service.AuthorService;
import homework9.service.BookService;
import homework9.service.CommentService;
import homework9.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    public void shouldReturnBooksPage() throws Exception {
        List<Book> books = Arrays.asList(
                new Book(1L, "Над осевшими могилами", new Author("Джесс Уолтер"), new Genre("Детектив"), 3, null),
                new Book(2L, "Название книги 2", new Author("Автор 2"), new Genre("Жанр 2"), 5, null)
        );

        when(bookService.findAllBooks()).thenReturn(books);
        when(authorService.findAllAuthors()).thenReturn(new ArrayList<>());
        when(genreService.findAllGenres()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", books))
                .andExpect(content().string(containsString("Джесс Уолтер")))
                .andExpect(content().string(containsString("Детектив")));
    }

    @Test
    public void shouldReturnEditBookPage() throws Exception {
        long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);

        when(bookService.findBookById(bookId)).thenReturn(Optional.of(book));
        when(authorService.findAllAuthors()).thenReturn(Collections.emptyList());
        when(genreService.findAllGenres()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/editBook").param("id", String.valueOf(bookId)))
                .andExpect(status().isOk())
                .andExpect(view().name("editBook"))
                .andExpect(model().attributeExists("book", "authors", "genres"))
                .andExpect(model().attribute("book", book));
    }

    @Test
    public void shouldSaveBook() throws Exception {
        Book book = new Book();
        book.setTitle("Test Book");

        mockMvc.perform(post("/editBook")
                        .param("title", book.getTitle()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(bookService, times(1)).saveBook(book);
    }

    @Test
    public void shouldReturnAddBookPage() throws Exception {
        when(authorService.findAllAuthors()).thenReturn(Collections.emptyList());
        when(genreService.findAllGenres()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/addBook"))
                .andExpect(status().isOk())
                .andExpect(view().name("addBook"))
                .andExpect(model().attributeExists("book", "authors", "genres"));
    }

    @Test
    public void shouldAddBook() throws Exception {
        Author author = new Author();
        author.setName("Test Author");

        Genre genre = new Genre();
        genre.setGenre("Test Genre");

        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor(author);
        book.setGenre(genre);

        mockMvc.perform(post("/addBook")
                        .param("title", book.getTitle())
                        .param("author.name", book.getAuthor().getName())
                        .param("genre.genre", book.getGenre().getGenre()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(bookService, times(1)).saveBook(book);
    }

    @Test
    public void shouldReturnCommentsPage() throws Exception {
        long bookId = 1L;
        List<Comment> comments = Arrays.asList(new Comment(), new Comment());

        when(commentService.findCommentsByBookId(bookId)).thenReturn(comments);

        mockMvc.perform(get("/findComment").param("id", String.valueOf(bookId)))
                .andExpect(status().isOk())
                .andExpect(view().name("comments"))
                .andExpect(model().attributeExists("comments"))
                .andExpect(model().attribute("comments", comments));
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        long bookId = 1L;

        mockMvc.perform(post("/deleteBook").param("id", String.valueOf(bookId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(bookService, times(1)).deleteBookById(bookId);
    }
}