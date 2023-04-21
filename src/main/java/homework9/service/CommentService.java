package homework9.service;

import homework9.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void saveComment(Comment comment);

    List<Comment> findCommentsByBookId(long id);

    Optional<Comment> findCommentById(long id);

    void deleteCommentById(long id);

}
