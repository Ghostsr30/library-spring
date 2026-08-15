package com.library.libraryspringjpa.config;

import com.library.libraryspringjpa.entities.*;
import com.library.libraryspringjpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;


@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;


    @Override
    public void run(String... args) throws Exception {

        Author at1 = new Author(null, "Stephen King", "EUA");
        Author at2 = new Author(null, "Tolkien", "England");
        Author at3 = new Author(null, "JK Rowling", "England");
        Author at4 = new Author(null, "Rick Riordan", "EUA");

        Category cat1 = new Category(null, "Romance");
        Category cat2 = new Category(null, "Suspense");
        Category cat3 = new Category(null, "Fantasy");
        Category cat4 = new Category(null, "Science Fiction");

        authorRepository.saveAll(Arrays.asList(at1, at2, at3, at4));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));

        Book b1 = new Book(null, "November '63", 1996, at1);
        Book b2 = new Book(null, "The Fellowship of the Ring", 1954, at2);
        Book b3 = new Book(null, "Harry Potter and the Philosopher's Stone", 1997, at3);
        Book b4 = new Book(null, "Percy Jackson & The Olympians: The Lightning Thief", 2005, at4);

        b1.getCategories().add(cat1);
        b1.getCategories().add(cat2);
        b1.getCategories().add(cat4);
        b2.getCategories().add(cat3);
        b3.getCategories().add(cat2);
        b3.getCategories().add(cat3);
        b4.getCategories().add(cat2);
        b4.getCategories().add(cat3);

        bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4));

        User u1 = new User(null, "Lucas Ferreira", "lucas@gmail.com");
        User u2 = new User(null, "Mariana Costa", "mariana@gmail.com");
        User u3 = new User(null, "Rafael Almeida", "rafael@gmail.com");
        User u4 = new User(null, "Beatriz Oliveira", "beatriz@gmail.com");

        userRepository.saveAll(Arrays.asList(u1,u2,u3,u4));

        Loan l1 = new Loan(null, LocalDate.of(2026, 7, 2), LocalDate.of(2026,8,2),null);

        l1.setUser(u1);

        l1.setBook(b3);

        loanRepository.save(l1);

    }
}
