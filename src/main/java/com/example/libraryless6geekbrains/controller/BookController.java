package com.example.libraryless6geekbrains.controller;

import com.example.libraryless6geekbrains.model.Author;
import com.example.libraryless6geekbrains.model.Book;
import com.example.libraryless6geekbrains.model.Reader;
import com.example.libraryless6geekbrains.repository.AuthorRepository;
import com.example.libraryless6geekbrains.repository.BookRepository;
import com.example.libraryless6geekbrains.repository.ReaderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ReaderRepository readerRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.readerRepository = readerRepository;
    }

    @GetMapping("/book")
    public List<Book> getAllBooks (){
        return bookRepository.findAll();
    }
    @GetMapping("/book/create")
    public Book addBook(@RequestParam String title ){
        Book book = new Book();
        book.setTitle(title);
        return bookRepository.save(book);
    }
    @GetMapping("/book/reader")
    public Reader getReaderOfBook(@RequestParam Long id){
        return readerRepository.findByReadBooksId(id);
    }
}
