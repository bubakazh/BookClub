package com.codingdojo.troy.bookclubtm.services;

import com.codingdojo.troy.bookclubtm.models.Book;
import com.codingdojo.troy.bookclubtm.repositories.BookRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepo;

    public BookService (BookRepository bookRepo){
        this.bookRepo = bookRepo;
    }

    public void create(@Valid Book book){
        bookRepo.save(book);
    }
    public List<Book> getAll() {
        return (List<Book>) bookRepo.findAll();
    }

    public Book getOne(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        return book.orElse(null);
    }

    public void update(@Valid Book book){
        bookRepo.save(book);
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}
