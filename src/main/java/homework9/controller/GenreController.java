package homework9.controller;

import homework9.domain.Genre;
import homework9.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres")
    public String listPage(Model model) {
        List<Genre> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        return "genres";
    }
}
