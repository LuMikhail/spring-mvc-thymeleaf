package homework9.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment_book")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Comment(id=%s ,comment=%s ,book=%s)",
                this.id,
                this.comment,
                this.book);
    }
}
