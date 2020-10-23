package com.example.libraryless6geekbrains.controller;

import com.example.libraryless6geekbrains.model.Author;
import com.example.libraryless6geekbrains.model.Book;
import com.example.libraryless6geekbrains.model.Reader;
import com.example.libraryless6geekbrains.repository.BookRepository;
import com.example.libraryless6geekbrains.repository.ReaderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ReaderController {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public ReaderController(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    @GetMapping("/reader")
    public List<Reader> getAllReaders (){



        return readerRepository.findAll();
    }
    @GetMapping("/reader/create")
    public Reader addReader(@RequestParam String name ){
        Reader reader = new Reader();
        reader.setName(name);
        return readerRepository.save(reader);
    }
    @GetMapping("/reader/addbook")
    public Reader addBookToReader(@RequestParam Long bookId,Long readerId){
        Book book = bookRepository.getOne(bookId);
        Reader reader = readerRepository.getOne(readerId);
        reader.getReadBooks().add(book);
        return reader;
    }

    @GetMapping("/reader/allbooks")
    public List<Book> allBooksOfReader(@RequestParam Long id){
        return bookRepository.findAllByReaderId(id);
    }

}