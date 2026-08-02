package com.library.libraryspringjpa.repositories;

import com.library.libraryspringjpa.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
