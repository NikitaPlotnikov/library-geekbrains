package com.example.libraryless6geekbrains.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("book")
    private List<Book> writeBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getWriteBook() {
        return writeBook;
    }

    public void setWriteBook(List<Book> writeBook) {
        this.writeBook = writeBook;
    }
}
