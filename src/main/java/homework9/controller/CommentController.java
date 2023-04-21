package homework9.controller;

import homework9.domain.Comment;
import homework9.exception.EntityNotFoundException;
import homework9.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam("id") long id, Model model) {
        Comment comment = commentService
                .findCommentById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
        model.addAttribute("comment", comment);
        return "comments";
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam("id") long id) {
        commentService.deleteCommentById(id);
        return "redirect:/";
    }
}
