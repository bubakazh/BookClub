package com.codingdojo.troy.bookclubtm.controllers;

import com.codingdojo.troy.bookclubtm.models.Book;
import com.codingdojo.troy.bookclubtm.models.User;
import com.codingdojo.troy.bookclubtm.services.BookService;
import com.codingdojo.troy.bookclubtm.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final UserService userSvc;
    private final BookService bookSvc;

    public BookController(BookService bookSvc, UserService userSvc) {
        this.bookSvc = bookSvc;
        this.userSvc = userSvc;
    }

    // ! CREATE
    @GetMapping("/newbook")
    public String newBook(@ModelAttribute("book") Book book, Model model, HttpSession session){
        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long id = (Long) session.getAttribute("userId");
        User user = userSvc.findById(id);
        model.addAttribute("user", user);
        return "newBook.jsp";
    }

    @PostMapping("/books")
    public String createBook(@ModelAttribute("book") com.codingdojo.troy.bookclubtm.models.@Valid Book book, BindingResult result){
        if(result.hasErrors()){
            return "newBook.jsp";
        } else {
            bookSvc.create(book);
            return "redirect:/books";
        }
    }

    // ! READ ALL
    @GetMapping("/books")
    public String allBooks(Model model, HttpSession session){
        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        List<com.codingdojo.troy.bookclubtm.models.Book> books = bookSvc.getAll();
        model.addAttribute("books", books);
        Long userId = (Long) session.getAttribute("userId");
        User user = userSvc.findById(userId);
        model.addAttribute("user", user);
        return "books.jsp";
    }

    // ! READ ONE
    @GetMapping("/books/{id}")
    public String oneBook(HttpSession session, Model model, @PathVariable("id")Long id){
        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Book book = bookSvc.getOne(id);
        model.addAttribute("book", book);
        Long userId = (Long) session.getAttribute("userId");
        User user = userSvc.findById(userId);
        model.addAttribute("user", user);
        return "showBook.jsp";
    }

    // ! EDIT
    @GetMapping("/books/edit/{id}")
    public String editBook(HttpSession session, Model model, @PathVariable("id")Long id){
        Book book = bookSvc.getOne(id);
        model.addAttribute("book", book);
        Long userId = (Long) session.getAttribute("userId");
        User user = userSvc.findById(userId);
        model.addAttribute("user", user);
        return "editBook.jsp";
    }

    @PutMapping("/books/{id}")
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result){
        if(result.hasErrors()){
            return "editBook.jsp";
        } else {
            bookSvc.update(book);
            return "redirect:/books/{id}";
            // ! CHANGE THE REDIRECT TO GO TO THE BOOK THAT WAS UPDATED INSTEAD OF ALL BOOKS!
        }
    }

    // ! DELETE
    @DeleteMapping("/books/destroy/{id}")
    public String destroy(@PathVariable("id")Long id){
        bookSvc.deleteBook(id);
        return "redirect:/books";
    }
}
