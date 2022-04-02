package ru.tsu.hits.springdb1.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @Column(name="id")
    private String uuid;

    @Column
    private String name;

    @Column (name="book_author")
    private String author;

    @Temporal(TemporalType.DATE)
    @Column(name="release_date")
    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    @Column
    private Genre genre;

}
