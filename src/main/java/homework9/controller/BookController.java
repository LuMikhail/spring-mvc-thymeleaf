package homework9.controller;

import homework9.domain.Author;
import homework9.domain.Book;
import homework9.domain.Comment;
import homework9.domain.Genre;
import homework9.exception.EntityNotFoundException;
import homework9.service.AuthorService;
import homework9.service.BookService;
import homework9.service.CommentService;
import homework9.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CommentService commentService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/editBook")
    private String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService
                .findBookById(id).orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        model.addAttribute("book", book);
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        List<Genre> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String saveBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/";
    }

    @GetMapping("/addBook")
    public String addPage(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("genres", genreService.findAllGenres());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") @Valid Book book) {
        bookService.saveBook(book);
        return "redirect:/";
    }

    @GetMapping("/findComment")
    public String findPage(@RequestParam("id") long id, Model model) {
        List<Comment> comments = commentService.findCommentsByBookId(id);
        model.addAttribute("comments", comments);
        return "comments";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") long id) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }
}
