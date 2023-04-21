package homework9.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "author")
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_author", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Author(id=%s ,name=%s)",
                this.id,
                this.name);
    }
}
