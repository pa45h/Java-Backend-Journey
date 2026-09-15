package org.pa45h.agecalcspring.Controller;

import org.pa45h.agecalcspring.Model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    List<Book> books = new ArrayList<>();

    @GetMapping("/")
    public String viewBooks(Model model) {
        model.addAttribute("books", books);
        model.addAttribute("book", new Book());
        return "books";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String title, @RequestParam String author) {
        books.add(new Book(title, author));
        return "redirect:/";
    }
}
