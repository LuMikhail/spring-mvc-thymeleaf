package homework9.controller;

import homework9.domain.Author;
import homework9.exception.EntityNotFoundException;
import homework9.service.AuthorService;
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
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public String listPage(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/editAuthor")
    public String editPage(@RequestParam("id") long id, Model model) {
        Author author = authorService
                .findAuthorById(id).orElseThrow(() -> new EntityNotFoundException("Author not found with id:" + id));
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @PostMapping("/editAuthor")
    public String saveAuthor(Author author) {
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/addAuthor")
    public String addPage(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "addAuthor";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Name cannot be blank");
            return "addAuthor";
        }
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @PostMapping("/delete")
    public String deleteAuthor(@RequestParam("id") long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/authors";
    }
}
