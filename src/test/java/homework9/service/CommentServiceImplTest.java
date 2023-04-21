package homework9.service;


import homework9.domain.Comment;
import homework9.repository.BookRepository;
import homework9.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class CommentServiceImplTest {

    private static final long COMMENT_ID = 1;

    @Configuration
    @Import({CommentServiceImpl.class})
    static class NestedTestConfiguration {
    }

    @MockBean
    private CommentRepository commentDao;

    @MockBean
    private BookRepository bookDao;

    @Autowired
    private CommentServiceImpl commentService;

    @Test
    void calledCorrectlyMethodInsertComment() {
        Comment comment = new Comment("Рекомендую к прочтению");
        commentService.saveComment(new Comment("Рекомендую к прочтению"));
        verify(commentDao).save(comment);
    }

    @Test
    void calledCorrectlyMethodFindCommentById() {
        commentService.findCommentById(COMMENT_ID);
        verify(commentDao).findById(COMMENT_ID);
    }


    @Test
    void calledCorrectlyMethodDeleteCommentById() {
        commentService.deleteCommentById(COMMENT_ID);
        verify(commentDao).deleteById(COMMENT_ID);
    }
}