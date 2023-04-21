package homework9.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "genre")
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_genre", nullable = false)
    private String genre;

    public Genre(long id) {
        this.id = id;
    }

    public Genre(String genre) {
        this.genre = genre;
    }
}
