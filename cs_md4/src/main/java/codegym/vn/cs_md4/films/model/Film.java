package codegym.vn.cs_md4.films.model;

import javax.persistence.*;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
