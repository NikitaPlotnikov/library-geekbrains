package com.example.libraryless6geekbrains.controller;

import com.example.libraryless6geekbrains.model.Author;
import com.example.libraryless6geekbrains.model.Book;
import com.example.libraryless6geekbrains.model.Reader;
import com.example.libraryless6geekbrains.repository.AuthorRepository;
import com.example.libraryless6geekbrains.repository.BookRepository;
import com.example.libraryless6geekbrains.repository.ReaderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/author")
    public List<Author> getAllAuthors (){
        return authorRepository.findAll();
    }

    @GetMapping("/author/create")
    public Author addAuthor(@RequestParam String name ){
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }

    @GetMapping("/author/addbook")
    public Author addBookToAuthor(@RequestParam Long bookId,Long authorId){
        Book book = bookRepository.getOne(bookId);
        Author author = authorRepository.getOne(authorId);
        author.getWriteBook().add(book);
        return author;
    }

    @GetMapping("/author/books")
    public List<Book> getBooksByAuthor(@RequestParam Long id){
        return bookRepository.findAllByAuthorId(id);
    }

    @GetMapping("/author/books/readers")
    public List<Reader> getReadersBooksByAuthor(@RequestParam Long id){
        Author author = authorRepository.getOne(id);
        List<Book> books = author.getWriteBook();
        ArrayList<Reader> readers = new ArrayList<>();
        for (Book b: books){
            readers.add(b.getReader());
        }
        return readers;
    }
}
