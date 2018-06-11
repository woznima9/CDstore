package mw.hibernate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Cds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    public int duration;

    @Column(name = "has_hard_cover")
    public byte hasHardCover;

    @Column(name = "release_date")
    public LocalDate releaseDate;


}
