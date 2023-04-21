package homework9.controller;

import homework9.domain.Author;
import homework9.repository.AuthorRepository;
import homework9.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void shouldReturnListPage() throws Exception {
        // Mock author data
        Author author1 = new Author(1L, "Author 1");
        Author author2 = new Author(2L, "Author 2");
        List<Author> authors = Arrays.asList(author1, author2);

        // Mock AuthorService to return the list of authors
        when(authorService.findAllAuthors()).thenReturn(authors);

        // Perform GET request to /authors endpoint
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attribute("authors", authors));
    }

    @Test
    public void shouldReturnEditAuthorPage() throws Exception {
        long authorId = 1L;
        Author author = new Author(authorId, "Author 1");

        // Mock AuthorService to return the author
        when(authorService.findAuthorById(authorId)).thenReturn(Optional.of(author));

        // Perform GET request to /editAuthor endpoint with authorId as query parameter
        mockMvc.perform(get("/editAuthor").param("id", String.valueOf(authorId)))
                .andExpect(status().isOk())
                .andExpect(view().name("editAuthor"))
                .andExpect(model().attributeExists("author"))
                .andExpect(model().attribute("author", author));
    }

    @Test
    public void shouldSaveAuthor() throws Exception {
        Author author = new Author(1L, "Антонио Гарридо");

        // Perform POST request to /editAuthor endpoint with author data
        MvcResult result = mockMvc.perform(post("/editAuthor")
                        .param("id", String.valueOf(author.getId()))
                        .param("name", author.getName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authors"))
                .andReturn();

        verify(authorService, times(1)).saveAuthor(author);
    }

    @Test
    public void shouldAddAuthor() throws Exception {
        Author author = new Author();
        when(authorRepository.save(author)).thenReturn(author);

        mockMvc.perform(post("/addAuthor")
                        .flashAttr("author", author))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("addAuthor"));
    }

    @Test
    public void shouldDeleteAuthor() throws Exception {
        long authorId = 1L;

        // Perform POST request to "/delete" endpoint with id as a parameter
        mockMvc.perform(post("/delete")
                        .param("id", String.valueOf(authorId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authors"));

        verify(authorService, times(1)).deleteAuthorById(authorId);
    }
}